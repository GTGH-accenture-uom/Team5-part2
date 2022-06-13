package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.exceptions.VaccinationCenterNotFoundException;
import team5.model.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class VaccinationCenterService {


    private final List<VaccinationCenter> allVaccinationCenters = new ArrayList<>();
//    private InsuredService insuredService;
//    private DoctorService doctorService;
//    private VaccinationService vaccinationService;

//
//    @Autowired
//    public VaccinationCenterService(InsuredService insuredService, DoctorService doctorService, VaccinationService vaccinationService) {
//        this.insuredService = insuredService;
//        this.doctorService = doctorService;
//        this.vaccinationService = vaccinationService;
//    }

    public VaccinationCenter createVaccinationCenter(String code, String city, String address) {
        VaccinationCenter vaccinationCenter = new VaccinationCenter(code, city, address);
        allVaccinationCenters.add(vaccinationCenter);
        return vaccinationCenter;
    }

    //we need to apply restrictions to this
    public void addTimeslotsToVaccinationCenter(List<Timeslot> timeslots, VaccinationCenter vaccinationCenter) {
        for (Timeslot t : timeslots) {
            if (!vaccinationCenter.getTimeslots().contains(t)) {
                vaccinationCenter.addTimeSlot(t);
            }
        }
    }


    //getTimeslotsByLocalDateTimeByDoctor(String amkaInsured, LocalDateTime localDateTime, String amkaDoctor)


//    public void createReservation(Insured insured, Timeslot timeSlot, VaccinationCenter vaccinationCenter) {
//        if (insured!=null && timeSlot!=null && vaccinationCenter!=null
//                && timeSlot.getDoctor()!=null){
//            Reservation reservation = new Reservation(insured, timeSlot);
//            vaccinationCenter.addReservation(reservation);
//            timeSlot.setAvailable(false);
//        }else{
//            System.err.println("Cannot make this reservation with insured amka:" +insured.getAmka() + ", " +
//                    "timeslot" + timeSlot + ", center with code " + vaccinationCenter.getCode());
//        }
//    }



//    public String getAllReservationsPerCenter() {
//        String str = "---------All RERSERVATIONS PER CENTER---------\n";
//        for (VaccinationCenter v : allVaccinationCenters) {
//            str += getReservations(v);
//        }
//        return str;
//    }



    /*
    public String getFreeTimeslotsByVaccinationCenter() {
        String str = "---------LIST OF FREE TIMESLOTS PER VACCINATION CENTER---------\n";
        for (VaccinationCenter vc : allVaccinationCenters) {
            str += "Vaccination center no." + vc.getCode() + " has free timeslots: " + getFreeTimeslotsByVaccinationCenter(vc) + "\n";
        }
        return str;
    }

     */

    public VaccinationCenter findVaccinationCenterByCode(String code) {
        VaccinationCenter foundVaccinationCenter;
        Optional<VaccinationCenter> optionalVaccinationCenter = allVaccinationCenters
                .stream()
                .filter(vaccCent -> vaccCent.getCode().equals(code))
                .findFirst();
        if (optionalVaccinationCenter.isPresent()) {
            foundVaccinationCenter = optionalVaccinationCenter.get();
        } else {
            throw new VaccinationCenterNotFoundException(code);
        }
        return foundVaccinationCenter;
    }

    public List<VaccinationCenter> getAllVaccinationCenters() {
        return allVaccinationCenters;
    }


    //2st requirement
    /*
    public void displayFreeTimeslotsByVaccinationCenter() {
        System.out.println(getFreeTimeslotsByVaccinationCenter());

    }

     */

}