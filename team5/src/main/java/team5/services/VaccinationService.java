package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.exceptions.DoctorNotFoundException;
import team5.exceptions.ExistingRecordException;
import team5.exceptions.InsuredNotFoundException;
import team5.exceptions.ReservationNotFoundException;
import team5.model.*;
import team5.utilities.MessagesForExistingValues;
import team5.utilities.VaccinationState;

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
                    names.add(v.getVacc_brand());
                }
            } else {
                throw new RuntimeException("Cannot get vaccinations by insured.");
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
                if (v.getVacc_brand().equals(vaccine) && v.getExpirationDate().isAfter(expirationDateTime)) {
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
                .filter(vaccination -> vaccination.getTimeslot().getId() == id).findFirst();
        if (optionalVaccination.isPresent()) {
            foundVaccination = optionalVaccination.get();
        }
        return foundVaccination;
    }


    public Vaccination createVaccination(Vaccination vaccination, String doctorAmka) {

        long timeslotId = vaccination.getTimeslot().getId();
        String insuredAmka = vaccination.getInsured().getAmka();
        Doctor doctor = doctorService.findDoctorByAmka(doctorAmka);
        Insured insured = insuredService.findInsuredByAmka(insuredAmka);

        if (findVaccinationByTimeslotId(timeslotId) != null) {
            throw new ExistingRecordException(MessagesForExistingValues.VACCINATION_ALREADY_MADE.getErrorMessage());
        }
        if (insured == null) {
            throw new InsuredNotFoundException(insuredAmka);
        }
        Reservation foundReservation = reservationService.findReservationByTimeslotId(timeslotId);

        if (foundReservation != null) {
            Timeslot timeslot = foundReservation.getTimeslot();
            LocalDateTime startDateTime = vaccination.getExpirationDate();
            LocalDateTime expirationDate = vaccination.getExpirationDate();
            vaccination = new Vaccination(insured, doctor, startDateTime, expirationDate);
            vaccination.setTimeslot(timeslot);
            timeslot.setVaccination(vaccination);
            //Add record of vaccination to vaccination center
            VaccinationCenter vaccinationCenter = vaccination.getTimeslot().getVaccinationCenter();
            vaccinationCenter.addVaccination(vaccination);
            //Add vaccination in doctor's vaccinations list
            doctor.addVaccination(vaccination);
            allVaccinations.add(vaccination);
            return vaccination;
        } else {
            throw new ReservationNotFoundException(timeslotId);
        }
    }

    //Second method for Create Vaccination not to be used from the api
    public Vaccination createVaccination(String brand, int yearsToExpire, Insured insured, VaccinationCenter vaccinationCenter) {
        Reservation foundReservation = reservationService.findReservationByInsuredAmka(insured, vaccinationCenter);

        if (foundReservation != null) {
            Insured insuredToVaccinate = foundReservation.getInsured();
            Doctor doctor = foundReservation.getTimeslot().getDoctor();
            LocalDateTime startDateTime = foundReservation.getTimeslot().getStartDateTime();
            LocalDateTime expirationDate = startDateTime.plusYears(yearsToExpire);
            Vaccination vaccination = new Vaccination(brand, insuredToVaccinate, doctor, startDateTime, expirationDate);
            //Add record of vaccination to vaccination center
            vaccinationCenter.addVaccination(vaccination);
            //Add vaccination in doctor's vaccinations list
            doctor.addVaccination(vaccination);
            getAllVaccinations().add(vaccination);
            return vaccination;
        } else {
            System.err.println("This Vaccination cannot be made because this reservation cannot be found");
        }
        return null;
    }

}
