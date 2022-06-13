package team5.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Reservation {
    private static final AtomicLong count = new AtomicLong(0);
    private final long id;
    private Insured insured;
    private Timeslot timeslot;



    public Reservation(Insured insured, Timeslot timeslot) {
        this.insured = insured;
        this.timeslot = timeslot;
        this.id = count.incrementAndGet();
    }

    public long getId() { return id; }

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
                ", Id = " + id +
                "insured=" + insured +
                ", timeslot=" + timeslot +
                '}';
    }
}
