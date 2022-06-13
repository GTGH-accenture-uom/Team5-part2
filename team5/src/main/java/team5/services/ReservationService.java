package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.model.Doctor;
import team5.model.Insured;
import team5.model.Reservation;
import team5.model.Timeslot;
import team5.utilities.DateUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private TimeslotService timeslotService;
    private final DoctorService doctorService;
    private final InsuredService insuredService;
    private final List<Reservation> allReservations = new ArrayList<>();

    private int reservationCounter = 0;


    @Autowired
    public ReservationService(TimeslotService timeslotService, DoctorService doctorService, InsuredService insuredService) {
        this.timeslotService = timeslotService;
        this.doctorService = doctorService;
        this.insuredService = insuredService;
    }

    public List<Timeslot> getTimeslotsByLocalDateTimeByDoctor(String amkaInsured, LocalDateTime localDateTime, String amkaDoctor) {
        List<Timeslot> timeslotsByDate = getTimeslotsByLocalDateTime(timeslotService.getAllTimeslots(), localDateTime);
        List<Timeslot> timeslotsByDoctorByDate = getTimeslotsByDoctor(timeslotsByDate, amkaDoctor);
        return timeslotsByDoctorByDate;
    }

    public List<Timeslot> getTimeslotsByLocalDateTime(List<Timeslot> timeslots, LocalDateTime localDateTime) {
        List<Timeslot> timeslotsByDate = new ArrayList<>();
        for (Timeslot t : timeslots) {
            if (t.getStartDateTime().equals(localDateTime)) {
                timeslotsByDate.add(t);
            }
        }
        return timeslotsByDate;
    }

    public List<Timeslot> getTimeslotsByDoctor(List<Timeslot> timeslots, String amka) {
        List<Timeslot> timeslotsByDoctor = new ArrayList<>();
        for (Timeslot t : timeslots) {
            if (t.getDoctor() != null && t.getDoctor().getAmka().equals(amka)) {
                timeslotsByDoctor.add(t);
            }
        }
        return timeslotsByDoctor;
    }


    public Reservation findReservationById(String reservationId) {
        long id = Long.parseLong(reservationId);
        Optional<Reservation> optionalReservation = getAllReservations().stream().filter(reservation -> reservation.getId() == id).findFirst();
        if (optionalReservation.isEmpty()) {
            //to-do ReservationNotFoundException
            throw new RuntimeException("Reservation Not found");
        }
        return optionalReservation.get();

    }

    public String updateReservation(String reservationId, String timeslotId) {
        Reservation reservation = findReservationById(reservationId);
        Timeslot foundTimeslot = timeslotService.findTimeslotById(Integer.parseInt(timeslotId));
        System.out.println("->>>> " + foundTimeslot);
        if (foundTimeslot != null && reservationCounter < 2) {
            reservationCounter++;
            //Set free the previous timeslot
            reservation.getTimeslot().setAvailable(true);
            //Set the reservation of this timeslot to null
            reservation.getTimeslot().setReservation(null);
            //add the new timeslot
            reservation.setTimeslot(foundTimeslot);
            foundTimeslot.setReservation(reservation);
            //reserve the new timeslot
            foundTimeslot.setAvailable(false);
        } else {
            //to-do Reservation cannot be updated Exception
            throw new RuntimeException("Reservation cannot be updated");
        }
        return "Resevation UPDATED";
    }


    public long createReservation(String amkaInsured, String date, String amkaDoctor) {
        LocalDateTime localDateTime = DateUtils.stringToLocalDateTime(date);
        List<Timeslot> timeslots = getTimeslotsByLocalDateTimeByDoctor(amkaInsured, localDateTime, amkaDoctor);
        System.out.println(timeslots);
        Doctor doctor = doctorService.findDoctorByAmka(amkaDoctor);//
        Insured insured = insuredService.findInsuredByAmka(amkaInsured);
        if (insured != null && timeslots.size() > 0 && timeslots.get(0) != null && timeslots.get(0).getDoctor() != null) {//&& timeslots.get(0).getDoctor().equals(doctor)
            Timeslot timeslot = timeslots.get(0);
            Reservation reservation = new Reservation(insured, timeslot);
            timeslot.getVaccinationCenter().addReservation(reservation);
            System.out.println(reservation);
            doctor.addReservation(reservation);
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
        List<Timeslot> timeslots = getTimeslotsByLocalDateTimeByDoctor(amkaInsured, localDateTime, amkaDoctor);
        System.out.println(timeslots);
        Doctor doctor = doctorService.findDoctorByAmka(amkaDoctor);//
        Insured insured = insuredService.findInsuredByAmka(amkaInsured);
        if (insured != null && timeslots.size() > 0 && timeslots.get(0) != null && timeslots.get(0).getDoctor() != null) {//&& timeslots.get(0).getDoctor().equals(doctor)
            Timeslot timeslot = timeslots.get(0);
            Reservation reservation = new Reservation(insured, timeslot);
            timeslot.getVaccinationCenter().addReservation(reservation);
            System.out.println(reservation);
            doctor.addReservation(reservation);
            timeslot.setAvailable(false);
            allReservations.add(reservation);

            return reservation.getId();
        } else {
            System.err.println("Cannot make this reservation with insured " + insured + ", " + "timeslot" + timeslots);
            throw new RuntimeException("exception");
        }
    }

    public List<Reservation> getAllReservations() {
        return allReservations;
    }

}

