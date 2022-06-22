package team5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Vaccination {

    private static final AtomicLong count = new AtomicLong(0);
    private long id;
    private String vacc_type;
    private Insured insured;
    private Doctor doctor;
    private LocalDateTime vaccinationDate;
    private LocalDateTime expirationDate;
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


    public Vaccination(String vacc_type, Insured insured, Doctor doctor, LocalDateTime vaccinationDate, LocalDateTime expirationDate) {
        this(insured, doctor, vaccinationDate, expirationDate);
        this.vacc_type = vacc_type;
    }

    public long getId() {
        return id;
    }

    public String getVacc_type() {
        return vacc_type;
    }

    public void setVacc_type(String vacc_type) {
        this.vacc_type = vacc_type;
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
        if (vacc_type != null) {
            sb.append("vacc_brand='").append(vacc_type).append('\'');
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


