package team5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Reservation {
    private static final AtomicLong count = new AtomicLong(0);
    private final long id;
    private Insured insured;
    private Timeslot timeslot;

    private Vaccination vaccination;

    @JsonIgnore
    private int reservationChanges;

    public Reservation(Insured insured, Timeslot timeslot) {
        this.insured = insured;
        this.timeslot = timeslot;
        this.id = count.incrementAndGet();
    }

    public long getId() {
        return id;
    }

    public Insured getInsured() {
        return insured;
    }

    public void setInsured(Insured insured) {
        this.insured = insured;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }


    public int getReservationChanges() {
        return reservationChanges;
    }

    public void increaseReservationCounter() {
        reservationChanges++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return insured.equals(that.insured) && timeslot.equals(that.timeslot);
    }

    public Vaccination getVaccination() {
        return vaccination;
    }

    public void setVaccination(Vaccination vaccination) {
        this.vaccination = vaccination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(insured, timeslot);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reservation{");
        sb.append("id=").append(id);
        if (insured != null) {
            sb.append(", insured=").append(insured);
        }
        if (timeslot != null) {
            sb.append(", timeslot's id=").append(timeslot.getId());
            if (timeslot.getDoctor() != null) {
                sb.append(", doctor's amka=").append(timeslot.getDoctor().getAmka());
            }
            if (timeslot.getVaccinationCenter() != null) {
                sb.append(", vaccination center id=").append(timeslot.getVaccinationCenter().getCode());
            }
        }
        sb.append('}');
        return sb.toString();
    }

}
