package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.model.*;
import team5.utilities.VaccinationState;

import java.time.LocalDateTime;
import java.util.*;
@Service
public class VaccinationService {

    private final List<Vaccination> allVaccinations = new ArrayList<>();
    private ReservationService reservationService;


    @Autowired
    public VaccinationService( ReservationService reservationService) {
        this.reservationService = reservationService;

    }

    public List<Vaccination> getAllVaccinations() {
        return allVaccinations;
    }

    public List<Vaccination> getRecentVaccinationsByInsured(String amka){
        List<Vaccination> vaccinationsByInsured = new ArrayList<>();
        Set<String> names = new HashSet<>();
        for (Vaccination v: allVaccinations){
            if (v!=null && v.getInsured()!=null){
                if (v.getInsured().getAmka().equals(amka)){
                    vaccinationsByInsured.add(v);
                    names.add(v.getVacc_brand());
                }
            }else {
                throw new RuntimeException("Cannot get vaccinations by insured.");
            }
        }
        System.out.println("4*********************vaccinationsByInsured");
        System.out.println(vaccinationsByInsured);
        System.out.println("3*************************names");
        System.out.println(names);
        List<Vaccination> RecentVaccinationsByName = new ArrayList<>();
        for (String name: names){
            Vaccination vaccination = getVaccinationByInsuredByVaccine(name, amka, vaccinationsByInsured);
            RecentVaccinationsByName.add(vaccination);
        }
        System.out.println("1.*************************RecentVaccinationsByName");
        System.out.println(RecentVaccinationsByName);
        return RecentVaccinationsByName;
    }


    public  Vaccination getVaccinationByInsuredByVaccine(String vaccine, String amka, List<Vaccination> vaccinationsByInsured){
        try {
            LocalDateTime expirationDateTime = vaccinationsByInsured.get(0).getExpirationDate();
            Vaccination vaccination = vaccinationsByInsured.get(0);
            for (Vaccination v : vaccinationsByInsured) {
                if (v.getVacc_brand().equals(vaccine) && v.getExpirationDate().isAfter(expirationDateTime )) {
                    expirationDateTime = v.getExpirationDate();
                    vaccination = v;
                }
            }
            System.out.println("2.********************vaccination");
            System.out.println(vaccination);
            return vaccination;
        }catch (Exception e){
            throw new RuntimeException("Cannot parse vaccination list by insured bu id");
        }
    }

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
