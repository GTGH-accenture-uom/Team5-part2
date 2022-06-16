package team5.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.dto.VaccinationDTO;
import team5.dto.VaccinationWithStateDTO;
import team5.exceptions.*;
import team5.model.*;
import team5.utilities.MessagesForExistingValues;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class VaccinationService {

    private final List<Vaccination> allVaccinations = new ArrayList<>();
    private final QRCodeService qrCodeService;
    private ReservationService reservationService;
    private DoctorService doctorService;

    private InsuredService insuredService;

    private TimeslotService timeslotService;

    private final Logger logger = LoggerFactory.getLogger(VaccinationService.class);

    @Autowired
    public VaccinationService(ReservationService reservationService, DoctorService doctorService, InsuredService insuredService, TimeslotService timeslotService, QRCodeService qrCodeService) {
        this.reservationService = reservationService;
        this.doctorService = doctorService;
        this.insuredService = insuredService;
        this.timeslotService = timeslotService;
        this.qrCodeService = qrCodeService;
    }

    public Vaccination createVaccination(VaccinationDTO vaccinationDTO, String doctorAmka) {
        long timeslotId = vaccinationDTO.getTimeslotId();
        String insuredAmka = vaccinationDTO.getInsuredAmka();
        Reservation foundReservation = reservationService.findReservationByTimeslotId(timeslotId);
        if (foundReservation == null) {
            throw new ReservationNotFoundException(timeslotId);
        }
        logger.info("Reservation with id=" + foundReservation.getId());
        Doctor authorizedDoctor = reservationService.isReservationAssignedToDoctor(doctorAmka, timeslotId);
        if (authorizedDoctor != null) {
            if (findVaccinationByTimeslotId(timeslotId) != null) {
                throw new ExistingRecordException(MessagesForExistingValues.VACCINATION_ALREADY_MADE.name());
            }
            Insured foundInsured = insuredService.findInsuredByAmka(vaccinationDTO.getInsuredAmka());
            if (foundInsured == null) {
                throw new InsuredNotFoundException(insuredAmka);
            }
            LocalDateTime vaccinationDate = LocalDateTime.now();
            LocalDateTime expirationDate = vaccinationDTO.getExpirationDate();
            if (expirationDate.isBefore(vaccinationDate)) {
                throw new InvalidExpirationDateException(vaccinationDate);
            }
            String vacc_Name = vaccinationDTO.getVacc_Name();
            Vaccination vaccination = new Vaccination(vacc_Name, foundInsured, authorizedDoctor, vaccinationDate, expirationDate);
            vaccination.setReservation(foundReservation);
            foundReservation.setVaccination(vaccination);
            allVaccinations.add(vaccination);
            return vaccination;
        }
        throw new DoctorNotAuthorizedException(timeslotId);
    }


    public List<Vaccination> findRecentVaccinationsByInsured(String amka) {
        List<Vaccination> vaccinationsByInsured = new ArrayList<>();
        Set<String> names = new HashSet<>();
        for (Vaccination v : allVaccinations) {
            logger.warn("++" + v);
            if (v != null && v.getInsured() != null) {
                if (v.getInsured().getAmka().equals(amka)) {
                    vaccinationsByInsured.add(v);
                    names.add(v.getVacc_Name());
                }
            }
        }
        List<Vaccination> RecentVaccinationsByName = new ArrayList<>();
        for (String name : names) {
            Vaccination vaccination = findVaccinationByInsuredByVaccine(name, amka, vaccinationsByInsured);
            RecentVaccinationsByName.add(vaccination);
        }
        return RecentVaccinationsByName;
    }

    public List<VaccinationWithStateDTO> findAllRecentVaccinationsWithState(String amka) {
        List<Vaccination> recentVaccinations = findRecentVaccinationsByInsured(amka);
        List<VaccinationWithStateDTO> listDTO = new ArrayList<>();
        for (Vaccination v : recentVaccinations) {
            VaccinationWithStateDTO vaccinationWithStateDTO = new VaccinationWithStateDTO(v);
            listDTO.add(vaccinationWithStateDTO);
        }
        return listDTO;
    }


    public Vaccination findVaccinationByInsuredByVaccine(String vaccine, String amka, List<Vaccination> vaccinationsByInsured) {
        LocalDateTime expirationDateTime = vaccinationsByInsured.get(0).getExpirationDate();
        Vaccination vaccination = vaccinationsByInsured.get(0);
        for (Vaccination v : vaccinationsByInsured) {
            if (v.getVacc_Name().equals(vaccine) && v.getExpirationDate().isAfter(expirationDateTime)) {
                expirationDateTime = v.getExpirationDate();
                vaccination = v;
            }
        }
        System.out.println(vaccination);
        return vaccination;
    }

    public Vaccination findVaccinationByTimeslotId(long id) {
        return allVaccinations.stream()
                .filter(vaccination -> vaccination.getReservation().getTimeslot().getId() == id)
                .findAny()
                .orElse(null);
    }

    public String findRecentVaccinationStateInBrand(String brand, String insuredAmka) {
        List<VaccinationWithStateDTO> listOfVaccinationState = findAllRecentVaccinationsWithState(insuredAmka);
        VaccinationWithStateDTO vaccinationWithStateDTO = listOfVaccinationState
                .stream().filter(e -> e.getVaccination().getVacc_Name()
                        .equals(brand)).reduce((first, second) -> second)
                .orElse(null);
        if (vaccinationWithStateDTO != null) {
            return vaccinationWithStateDTO.getVaccinationState().name();
        }
        throw new VaccinationStateNotFoundException(brand, insuredAmka);
    }

    public byte[] generateQRCode(String vacc_brand, String insuredAmka, HttpServletResponse response) {
        String lastVaccStateInBrand = findRecentVaccinationStateInBrand(vacc_brand, insuredAmka);
        response.setContentType("image/png");
        logger.info("The last vaccination state for brand " + vacc_brand + " is " + lastVaccStateInBrand);
        return qrCodeService.generateQRCode(lastVaccStateInBrand, 500, 500);
    }

    public List<Vaccination> getAllVaccinations() {
        return allVaccinations;
    }
}





