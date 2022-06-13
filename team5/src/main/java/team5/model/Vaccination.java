package team5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import team5.utilities.VaccinationState;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Vaccination {

    private static final AtomicLong count = new AtomicLong(0);
    private long id;
    private String vacc_brand;
    private Insured insured;
    private Doctor doctor;
    private LocalDateTime vaccinationDate;
    private LocalDateTime expirationDate;
    private VaccinationState vaccinationState;

    private Timeslot timeslot;

    public Vaccination() {}

    public Vaccination(Insured insured, Doctor doctor, LocalDateTime vaccinationDate, LocalDateTime expirationDate) {
        this.insured = insured;
        this.doctor = doctor;
        this.vaccinationDate = vaccinationDate;
        this.expirationDate = expirationDate;
        this.id = count.incrementAndGet();
    }


    public Vaccination(String vacc_brand, Insured insured, Doctor doctor, LocalDateTime vaccinationDate, LocalDateTime expirationDate) {
        this(insured, doctor, vaccinationDate, expirationDate);
        this.vacc_brand = vacc_brand;
    }


    public long getId() {
        return id;
    }

    public String getVacc_brand() {
        return vacc_brand;
    }

    public void setVacc_brand(String vacc_brand) {
        this.vacc_brand = vacc_brand;
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

    public void setVaccinationState(VaccinationState vaccinationState) {
        this.vaccinationState = vaccinationState;
    }

    public VaccinationState getVaccinationState() {
        return vaccinationState;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vaccination{");
        sb.append("id='").append(id).append('\'');
        if (vacc_brand != null) {
            sb.append("vacc_brand='").append(vacc_brand).append('\'');
        }
        if (insured != null) {
            sb.append(", Insured=").append(insured);
        }
        if (doctor != null) {
            sb.append(", Doctor's amka=").append(doctor.getAmka());
        }
        sb.append(", vaccinationDate=").append(vaccinationDate);
        sb.append(", expirationDate=").append(expirationDate);
        if(timeslot!=null){
            sb.append(", timeslot=").append(timeslot.getId());
        }
        sb.append('}');
        return sb.toString();
    }

//    public VaccinationState getVaccinationStatus(){
//        if (this.getExpirationDate().isAfter(LocalDateTime.now())){
//            return VaccinationState.VALID;
//        }else{
//            return VaccinationState.EXPIRED;
//        }
//    }
}


