package team5.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Reservation {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private Insured insured;
    private Timeslot timeslot;


    public Reservation(Insured insured, Timeslot timeslot) {
        this.insured = insured;
        this.timeslot = timeslot;
        this.id = count.incrementAndGet();
    }

    public int getId() { return id; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return insured.equals(that.insured) && timeslot.equals(that.timeslot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insured, timeslot);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "insured=" + insured +
                ", timeslot=" + timeslot +
                '}';
    }
}
