package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.dto.ReservationDTO;
import team5.exceptions.ReservationCannotBeUpdated;
import team5.model.*;
import team5.utilities.DateUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReservationService {

    private final List<Reservation> allReservations = new ArrayList<>();
    private TimeslotService timeslotService;
    private final DoctorService doctorService;
    private final InsuredService insuredService;
    private final VaccinationCenterService vaccinationCenterService;


    @Autowired
    public ReservationService(TimeslotService timeslotService, DoctorService doctorService,
                              InsuredService insuredService, VaccinationCenterService vaccinationCenterService) {
        this.timeslotService = timeslotService;
        this.doctorService = doctorService;
        this.insuredService = insuredService;
        this.vaccinationCenterService = vaccinationCenterService;
    }

    public Reservation findReservationById(String reservationId) {
        long id = Long.parseLong(reservationId);
        Optional<Reservation> optionalReservation = allReservations
                .stream()
                .filter(reservation -> reservation.getId() == id).findFirst();
        if (optionalReservation.isEmpty()) {
            //to-do ReservationNotFoundException
            throw new RuntimeException("Reservation Not found");
        }
        return optionalReservation.get();
    }

    public Reservation findReservationById(long id) {
        Reservation foundReservation = null;
        Optional<Reservation> optionalReservation = allReservations
                .stream()
                .filter(reservation -> reservation.getId() == id).findFirst();
        if (optionalReservation.isPresent()) {
            foundReservation = optionalReservation.get();
        }
        return foundReservation;
    }


    public Reservation updateReservation(long reservationId, long timeslotId) {
        Reservation reservation = findReservationById(reservationId);
        Timeslot foundTimeslot = timeslotService.findTimeslotById(timeslotId);
        if (foundTimeslot != null && reservation.getReservationChanges() < 2) {
            //Set free the previous timeslot
            reservation.getTimeslot().setAvailable(true);
            //Set the reservation of this timeslot to null
            reservation.getTimeslot().setReservation(null);
            //add the new timeslot
            reservation.setTimeslot(foundTimeslot);
            foundTimeslot.setReservation(reservation);
            //reserve the new timeslot
            foundTimeslot.setAvailable(false);
            reservation.increaseReservationCounter();
        } else {
            throw new ReservationCannotBeUpdated(reservationId);
        }
        return reservation;
    }

    public long createReservation(String amkaInsured, String date, String amkaDoctor) {
        LocalDateTime localDateTime = DateUtils.stringToLocalDateTime(date);
        List<Timeslot> timeslots = timeslotService.getTimeslotsByLocalDateTimeByDoctor(amkaInsured, localDateTime, amkaDoctor);
        System.out.println(timeslots);
        Doctor doctor = doctorService.findDoctorByAmka(amkaDoctor);//
        Insured insured = insuredService.findInsuredByAmka(amkaInsured);
        if (insured != null && timeslots.size() > 0 && timeslots.get(0) != null && timeslots.get(0).getDoctor() != null) {//&& timeslots.get(0).getDoctor().equals(doctor)
            Timeslot timeslot = timeslots.get(0);
            Reservation reservation = new Reservation(insured, timeslot);
            //timeslot.getVaccinationCenter().addReservation(reservation);
            System.out.println(reservation);
            //doctor.addReservation(reservation);
            timeslot.setAvailable(false);
            allReservations.add(reservation);

            return reservation.getId();
        } else {
            System.err.println("Cannot make this reservation with insured " + insured + ", " + "timeslot" + timeslots);
            throw new RuntimeException("exception");
        }
    }

    public long createReservationBody(ReservationDTO body){
        if (body!=null && body.getAmkaInsured()!=null && body.getAmkaDoctor()!=null
            && body.getTimeslot()!=null && body.getTimeslot()!=null){
           return createReservation(body.getAmkaInsured(), body.getTimeslot(), body.getAmkaDoctor());
        }else{
            throw new RuntimeException("Reservation data provided are not correct.");
        }
    }

    public long createReservation(String amkaInsured, Timeslot timeslot2, String amkaDoctor) {
        LocalDateTime localDateTime = timeslot2.getStartDateTime();
        List<Timeslot> timeslots = timeslotService.getTimeslotsByLocalDateTimeByDoctor(amkaInsured, localDateTime, amkaDoctor);
        System.out.println(timeslots);
        Doctor doctor = doctorService.findDoctorByAmka(amkaDoctor);//
        Insured insured = insuredService.findInsuredByAmka(amkaInsured);
        if (insured != null && timeslots.size() > 0 && timeslots.get(0) != null && timeslots.get(0).getDoctor() != null) {//&& timeslots.get(0).getDoctor().equals(doctor)
            Timeslot timeslot = timeslots.get(0);
            Reservation reservation = new Reservation(insured, timeslot);
            System.out.println(reservation);
            timeslot.setAvailable(false);
            allReservations.add(reservation);
            return reservation.getId();
        } else {
            System.err.println("Cannot make this reservation with insured " + insured + ", " + "timeslot" + timeslots);
            throw new RuntimeException("exception");
        }
    }

    /*
    public String getReservations(VaccinationCenter vaccinationCenter) {
        String str = "";
        List<Reservation> reservations = vaccinationCenter.getReservations();
        if (!reservations.isEmpty()) {
            str += "---------Reservations of VaccinationCenter " + vaccinationCenter.getCode() + "---------\n";
            int count = 1;
            for (Reservation r : reservations) {
                str += count + "-" + r + "\n";
                count++;
            }
        } else {
            str += "No Reservations are made\n";
        }
        return str;
    }
     */


    public Reservation findReservationByTimeslotId(long id) {
        Reservation reservation = null;
        Optional<Reservation> optionalReservation = allReservations
                .stream()
                .filter(reserv -> reserv.getTimeslot().getId() == id).findFirst();
        if (optionalReservation.isPresent()) {
            reservation = optionalReservation.get();
        }
        return reservation;
    }

//    public Reservation findReservationByInsuredAmka(Insured insured, VaccinationCenter vaccinationCenter) {
//        Reservation reservation = null;
//        Optional<Reservation> optionalReservation = vaccinationCenter
//                .getReservations()
//                .stream()
//                .filter(reserv -> reserv.getInsured().getAmka().equals(insured.getAmka())).findFirst();
//        if (optionalReservation.isPresent()) {
//            reservation = optionalReservation.get();
//        }
//        return reservation;
//    }


//    public List<Reservation> getAllReservations() {
//        return allReservations;
//    }
//
//    public void findUserObject(Role role, String uniqueId){
//        if ()
//    }

    /////////////////////////////////////////////////////////////
    ////////    FILTERS
    /////////////////////////////////////////////////////////////

    //Filter by multiple filters
    public List<Reservation> findReservationsByAllFilters(String amkaInsured, String amkaDoctor) {
        List<Reservation> reservations = allReservations;
        System.out.println(allReservations);
        System.out.println("amkaInsured");
        System.out.println(amkaInsured);
        System.out.println("amkaDoctor");
        System.out.println(amkaDoctor);
        if (amkaInsured != null) {
            reservations = findReservationsByInsured(amkaInsured, reservations);
        }
        if (amkaDoctor != null) {
            reservations = findReservationsByDoctor(amkaDoctor, reservations);
        }
        return reservations;
    }

    //Filter By Insured
    public List<Reservation> findReservationsByInsured(Insured insured, List<Reservation> reservations) {
        List<Reservation> ReservationsByInsured = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getInsured().getAmka().equals(insured.getAmka())) {
                ReservationsByInsured.add(r);
            }
        }
        System.out.println("ReservationsByInsured");
        System.out.println(ReservationsByInsured);
        return ReservationsByInsured;
    }

    public List<Reservation> findReservationsByInsured(String amkaInsured, List<Reservation> reservations) {
        Insured insured = insuredService.findInsuredByAmka(amkaInsured);
        System.out.println("findReservationsByInsured(insured, reservations)");
        System.out.println(findReservationsByInsured(insured, reservations));
        return findReservationsByInsured(insured, reservations);
    }

    //filter By Doctor
    public List<Reservation> findReservationsByDoctor(Doctor doctor, List<Reservation> reservations) {
        List<Reservation> ReservationsByDoctor = new ArrayList<>();
        for (Reservation r : reservations) {
            System.out.println(r.getTimeslot().getDoctor().getAmka());
            System.out.println(doctor.getAmka());
            if (r.getTimeslot().getDoctor().getAmka().equals(doctor.getAmka())) {
                System.out.println(111111111);
                ReservationsByDoctor.add(r);
            }
        }
        System.out.println("ReservationsByDoctor");
        System.out.println(ReservationsByDoctor);
        return ReservationsByDoctor;
    }

    public List<Reservation> findReservationsByDoctor(String amkaDoctor, List<Reservation> reservations) {
        Doctor doctor = doctorService.findDoctorByAmka(amkaDoctor);
        System.out.println("findReservationsByDoctor(doctor, reservations)");
        System.out.println(findReservationsByDoctor(doctor, reservations));
        return findReservationsByDoctor(doctor, reservations);
    }
    //////////////////////////////////////////


    public List<Reservation> getAllReservations() {
        return allReservations;
    }
}

