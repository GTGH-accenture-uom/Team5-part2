package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.exceptions.ReservationCannotBeUpdated;
import team5.exceptions.ReservationNotFoundException;
import team5.exceptions.TimeslotNotFoundException;
import team5.model.*;
import team5.utilities.DateUtils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReservationService {

    private final List<Reservation> allReservations = new ArrayList<>();
    private TimeslotService timeslotService;
    private final DoctorService doctorService;
    private final InsuredService insuredService;


    @Autowired
    public ReservationService(TimeslotService timeslotService, DoctorService doctorService,
                              InsuredService insuredService) {
        this.timeslotService = timeslotService;
        this.doctorService = doctorService;
        this.insuredService = insuredService;
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
            System.out.println(reservation);
            timeslot.setAvailable(false);
            allReservations.add(reservation);

            return reservation.getId();
        } else {
            System.err.println("Cannot make this reservation with insured " + insured + ", " + "timeslot" + timeslots);
            throw new RuntimeException("exception");
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



    /////////////////////////////////////////////////////////////
    ////////    FILTERS
    /////////////////////////////////////////////////////////////

    //Filter by multiple filters
    public List<Reservation> findReservationsByAllFilters(
            String amkaInsured, String amkaDoctor, String code, String date) {
        List<Reservation> reservations = allReservations;
        System.out.println(allReservations);
        System.out.println("amkaInsured");
        System.out.println(amkaInsured);
        System.out.println("amkaDoctor");
        System.out.println(amkaDoctor);

        if (amkaDoctor != null && amkaDoctor !="") {
            reservations = findReservationsByDoctor(amkaDoctor, reservations);
            if (amkaInsured != null && amkaInsured !="") {
                reservations = findReservationsByInsured(amkaInsured, reservations);
            }
            if (code != null && code !="") {
                reservations = findReservationsByCenter(code, reservations);
            }
            if (date != null && date !="") {
                reservations = findReservationsByDate(date, reservations);
            }
            return reservations;
        } else {
            throw new RuntimeException("The doctor's AMKA provided is not correct.");
        }

    }

    //Filter By date
    public List<Reservation> findReservationsByDate(String date, List<Reservation> reservations) {
        List<Reservation> findReservationsByDate = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getTimeslot().getStartDateTime().toString().equals(date)) {
                findReservationsByDate.add(r);
            }
        }
        return findReservationsByDate;
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

    //Filter By Vaccination Center
    public List<Reservation> findReservationsByCenter(String code, List<Reservation> reservations) {
        List<Reservation> ReservationsByCenter = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getTimeslot().getVaccinationCenter().getCode().equals(code)) {
                ReservationsByCenter.add(r);
            }
        }
        System.out.println("ReservationsByCenter");
        System.out.println(ReservationsByCenter);
        return ReservationsByCenter;
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

    public void deleteReservation(long id) {
        if (allReservations.removeIf(e -> e.getId() == id)) {
            System.out.println("removed");
        } else {
            throw new ReservationNotFoundException(id);
        }
    }

    public List<Reservation> getAllReservations() {
        return allReservations;
    }
}

