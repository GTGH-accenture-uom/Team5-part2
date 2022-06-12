package team5.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Doctor {

    private String amka;
    private String name;
    private String surname;
    private List <VaccinationCenter> vaccinationCenters = new ArrayList<>();
    private List<Vaccination> vaccinations = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public Doctor(String amka, String name, String surname) {
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

    public List<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(List<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public void addVaccination(Vaccination vaccination) {
        vaccinations.add(vaccination);
    }

    public List<VaccinationCenter> getVaccinationCenters() {
        return vaccinationCenters;
    }

    public void setVaccinationCenters(List<VaccinationCenter> vaccinationCenters) {
        this.vaccinationCenters = vaccinationCenters;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
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
        sb.append(", vaccinationCenter=").append(vaccinationCenters);
        sb.append(", vaccinations=").append(vaccinations);
        sb.append(", reservations=").append(reservations);
        sb.append('}');
        return sb.toString();
    }
}
