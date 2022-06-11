package team5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Timeslot {

    private static final AtomicLong count = new AtomicLong(0);
    private final long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean isAvailable;
    private int duration;
    private Doctor doctor;
    private VaccinationCenter vaccinationCenter;
    private Reservation reservation;

    public Timeslot(LocalDateTime startDateTime, int duration) {
        this.startDateTime = startDateTime;
        this.isAvailable = true;
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
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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
        sb.append("localDateTime=").append(startDateTime);
        sb.append(", endLocalDateTime=").append(endDateTime);
        sb.append(", duration=").append(duration);
        sb.append('}');
        return sb.toString();
    }
}
