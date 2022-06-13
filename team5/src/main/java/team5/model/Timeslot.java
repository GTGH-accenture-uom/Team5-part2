package team5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Timeslot {

    private static final AtomicLong count = new AtomicLong(0);

    private long id;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private boolean available;
    @JsonIgnore
    private int duration;
    private Doctor doctor;
    @JsonIgnore
    private VaccinationCenter vaccinationCenter;
    @JsonIgnore
    private Reservation reservation;
    @JsonIgnore
    private Vaccination vaccination;

    public Timeslot(){


    }
    public Timeslot(LocalDateTime startDateTime, int duration) {
        this.startDateTime = startDateTime;
        this.available = true;
        this.duration = duration;
        this.endDateTime = startDateTime.plusMinutes(duration);
        this.id = count.incrementAndGet();
    }

    public long getId() { return id; }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Vaccination getVaccination() {
        return vaccination;
    }

    public void setVaccination(Vaccination vaccination) {
        this.vaccination = vaccination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timeslot timeslot = (Timeslot) o;
        return duration == timeslot.duration && startDateTime.equals(timeslot.startDateTime) && endDateTime.equals(timeslot.endDateTime) && doctor.equals(timeslot.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime, duration, doctor);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Timeslot{");
        sb.append("id=").append(id);
        sb.append(", startDateTime=").append(startDateTime);
        sb.append(", endDateTime=").append(endDateTime);
        sb.append(", available=").append(available);
        sb.append(", duration=").append(duration);
        if(doctor!=null){
            sb.append(", doctor's amka=").append(doctor.getAmka());
        }
        if(vaccinationCenter!=null){
            sb.append(", vaccinationCenter's code=").append(vaccinationCenter.getCode());
        }
        if(reservation!=null){
            sb.append(", reservation's id =").append(reservation.getId());
            sb.append(", with insured amka= ").append(reservation.getInsured().getAmka());
        }

        sb.append('}');
        return sb.toString();
    }
}
