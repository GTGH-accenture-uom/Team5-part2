package team5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Timeslot {

    private static final AtomicLong count = new AtomicLong(0);
    @JsonIgnore
    private final long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    @JsonIgnore
    private boolean available;
    @JsonIgnore
    private int duration;

    private Doctor doctor;
    @JsonIgnore
    private VaccinationCenter vaccinationCenter;
    @JsonIgnore
    private Reservation reservation;

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
        sb.append("localDateTime=").append(startDateTime);
        sb.append(", endLocalDateTime=").append(endDateTime);
        sb.append(", duration=").append(duration);
        sb.append('}');
        return sb.toString();
    }
}
