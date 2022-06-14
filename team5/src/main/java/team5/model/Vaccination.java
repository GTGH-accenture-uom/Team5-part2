package team5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import team5.utilities.VaccinationState;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Vaccination {

    private static final AtomicLong count = new AtomicLong(0);
    private long id;

    private String vacc_Name;
    private Insured insured;
    private Doctor doctor;
    private LocalDateTime vaccinationDate;
    private LocalDateTime expirationDate;
    //private VaccinationState vaccinationState;
    @JsonIgnore
    private Reservation reservation;

    public Vaccination() {
    }

    public Vaccination(Insured insured, Doctor doctor, LocalDateTime vaccinationDate, LocalDateTime expirationDate) {
        this.insured = insured;
        this.doctor = doctor;
        this.vaccinationDate = vaccinationDate;
        this.expirationDate = expirationDate;
        this.id = count.incrementAndGet();
    }


    public Vaccination(String vacc_Name, Insured insured, Doctor doctor, LocalDateTime vaccinationDate, LocalDateTime expirationDate) {
        this(insured, doctor, vaccinationDate, expirationDate);
        this.vacc_Name = vacc_Name;
    }

    public long getId() {
        return id;
    }

    public String getVacc_Name() {
        return vacc_Name;
    }

    public void setVacc_Name(String vacc_Name) {
        this.vacc_Name = vacc_Name;
    }

    public Insured getInsured() {
        return insured;
    }

    public void setInsured(Insured insured) {
        this.insured = insured;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDateTime vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vaccination{");
        sb.append("id='").append(id).append('\'');
        if (vacc_Name != null) {
            sb.append("vacc_brand='").append(vacc_Name).append('\'');
        }
        if (insured != null) {
            sb.append(", Insured=").append(insured);
        }
        if (doctor != null) {
            sb.append(", Doctor's amka=").append(doctor.getAmka());
        }
        sb.append(", vaccinationDate=").append(vaccinationDate);
        sb.append(", expirationDate=").append(expirationDate);
        if (reservation != null) {
            sb.append(", reservation=").append(reservation.getId());
        }
        sb.append('}');
        return sb.toString();
    }

}


