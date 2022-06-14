package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.dto.VaccinationDTO;
import team5.exceptions.*;
import team5.model.*;
import team5.utilities.MessagesForExistingValues;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class VaccinationService {

    private final List<Vaccination> allVaccinations = new ArrayList<>();

    private ReservationService reservationService;
    private DoctorService doctorService;

    private InsuredService insuredService;

    private TimeslotService timeslotService;

    @Autowired
    public VaccinationService(ReservationService reservationService, DoctorService doctorService, InsuredService insuredService, TimeslotService timeslotService) {
        this.reservationService = reservationService;
        this.doctorService = doctorService;
        this.insuredService = insuredService;
        this.timeslotService = timeslotService;
    }

    public List<Vaccination> getAllVaccinations() {
        return allVaccinations;
    }

    public List<Vaccination> getRecentVaccinationsByInsured(String amka) {
        List<Vaccination> vaccinationsByInsured = new ArrayList<>();
        Set<String> names = new HashSet<>();
        for (Vaccination v : allVaccinations) {
            if (v != null && v.getInsured() != null) {
                if (v.getInsured().getAmka().equals(amka)) {
                    vaccinationsByInsured.add(v);
                    names.add(v.getVacc_Name());
                }
            }
        }
        System.out.println("4*********************vaccinationsByInsured");
        System.out.println(vaccinationsByInsured);
        System.out.println("3*************************names");
        System.out.println(names);
        List<Vaccination> RecentVaccinationsByName = new ArrayList<>();
        for (String name : names) {
            Vaccination vaccination = getVaccinationByInsuredByVaccine(name, amka, vaccinationsByInsured);
            RecentVaccinationsByName.add(vaccination);
        }
        System.out.println("1.*************************RecentVaccinationsByName");
        System.out.println(RecentVaccinationsByName);
        return RecentVaccinationsByName;
    }


    public Vaccination getVaccinationByInsuredByVaccine(String vaccine, String amka, List<Vaccination> vaccinationsByInsured) {
        try {
            LocalDateTime expirationDateTime = vaccinationsByInsured.get(0).getExpirationDate();
            Vaccination vaccination = vaccinationsByInsured.get(0);
            for (Vaccination v : vaccinationsByInsured) {
                if (v.getVacc_Name().equals(vaccine) && v.getExpirationDate().isAfter(expirationDateTime)) {
                    expirationDateTime = v.getExpirationDate();
                    vaccination = v;
                }
            }
            System.out.println("2.********************vaccination");
            System.out.println(vaccination);
            return vaccination;
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse vaccination list by insured bu id");
        }
    }

    public Vaccination findVaccinationByTimeslotId(long id) {
        Vaccination foundVaccination = null;
        Optional<Vaccination> optionalVaccination = allVaccinations
                .stream()
                .filter(vaccination -> vaccination.getReservation().getTimeslot().getId() == id)
                .findFirst();
        if (optionalVaccination.isPresent()) {
            foundVaccination = optionalVaccination.get();
        }
        return foundVaccination;
    }


    public Vaccination createVaccination(VaccinationDTO vaccinationDTO, String doctorAmka) {
        long timeslotId = vaccinationDTO.getTimeslotId();
        String insuredAmka = vaccinationDTO.getInsuredAmka();
        Timeslot foundTimeslot = timeslotService.findTimeslotById(timeslotId);
        if (findVaccinationByTimeslotId(timeslotId) != null) {
            throw new ExistingRecordException(MessagesForExistingValues.VACCINATION_ALREADY_MADE.getErrorMessage());
        }
        if (foundTimeslot == null) {
            throw new TimeslotNotFoundException(timeslotId);
        }
        Reservation foundReservation = reservationService.findReservationByTimeslotId(timeslotId);
        if (foundReservation == null) {
            throw new ReservationNotFoundException(timeslotId);
        }
        Insured foundInsured = insuredService.findInsuredByAmka(vaccinationDTO.getInsuredAmka());
        if (foundInsured == null) {
            throw new InsuredNotFoundException(insuredAmka);
        }
        Doctor foundDoctor = doctorService.findDoctorByAmka(doctorAmka);
        if (foundDoctor == null) {
            throw new DoctorNotFoundException(doctorAmka);
        }
        LocalDateTime vaccinationDate = LocalDateTime.now();
        LocalDateTime expirationDate = vaccinationDTO.getExpirationDate();
        if (expirationDate.isBefore(vaccinationDate)) {
            throw new InvalidExpirationDateException(vaccinationDate);
        }
        String vacc_Name = vaccinationDTO.getVacc_Name();
        Vaccination vaccination = new Vaccination(vacc_Name, foundInsured, foundDoctor, vaccinationDate, expirationDate);
        vaccination.setReservation(foundReservation);
        foundReservation.setVaccination(vaccination);
        // timeslot.setVaccination(vaccination);
        //Add record of vaccination to vaccination center
        //  VaccinationCenter vaccinationCenter = vaccination.getTimeslot().getVaccinationCenter();
        //vaccinationCenter.addVaccination(vaccination);
        //Add vaccination in doctor's vaccinations list
        //doctor.addVaccination(vaccination);
        allVaccinations.add(vaccination);
        return vaccination;
    }
}


//    //Second method for Create Vaccination not to be used from the api
//    public Vaccination createVaccination(String brand, int yearsToExpire, Insured insured, VaccinationCenter vaccinationCenter) {
//        Reservation foundReservation = reservationService.findReservationsByInsured(insured, List<Reservation> reservations);
//
//        if (foundReservation != null) {
//            Insured insuredToVaccinate = foundReservation.getInsured();
//            Doctor doctor = foundReservation.getTimeslot().getDoctor();
//            LocalDateTime startDateTime = foundReservation.getTimeslot().getStartDateTime();
//            LocalDateTime expirationDate = startDateTime.plusYears(yearsToExpire);
//            Vaccination vaccination = new Vaccination(brand, insuredToVaccinate, doctor, startDateTime, expirationDate);
//            //Add record of vaccination to vaccination center
//            //vaccinationCenter.addVaccination(vaccination);
//            //Add vaccination in doctor's vaccinations list
//            //doctor.addVaccination(vaccination);
//            getAllVaccinations().add(vaccination);
//            return vaccination;
//        } else {
//            System.err.println("This Vaccination cannot be made because this reservation cannot be found");
//        }
//        return null;
//    }


