package team5.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


public class Doctor {
    private static final AtomicLong count = new AtomicLong(0);

    private final long id;
    private String amka;
    private String name;
    private String surname;
    @JsonIgnore
    private List<Timeslot> timeslots = new ArrayList<>();

    public Doctor(String amka, String name, String surname) {
        this.id = count.incrementAndGet();
        this.amka = amka;
        this.name = name;
        this.surname = surname;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public List<Timeslot> getTimeslots() {
        return timeslots;
    }


    public void setTimeslots(List<Timeslot> timeslots) {
        this.timeslots = timeslots;
    }


    public void addTimeslot(Timeslot timeslot) {
        timeslots.add(timeslot);
        timeslot.setDoctor(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return amka.equals(doctor.amka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amka);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Doctor{");
        sb.append("amka='").append(amka).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
