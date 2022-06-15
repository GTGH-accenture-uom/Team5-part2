package team5.dto;

import team5.model.Reservation;
import team5.model.Vaccination;
import team5.model.VaccinationCenter;

public class ReservationGetDTO {

    private Reservation reservation;
    private VaccinationCenter vaccinationCenter;
    private Vaccination vaccination;

    public ReservationGetDTO(Reservation reservation, VaccinationCenter vaccinationCenter, Vaccination vaccination) {
        this.reservation = reservation;
        this.vaccinationCenter = vaccinationCenter;
        this.vaccination = vaccination;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    public Vaccination getVaccination() {
        return vaccination;
    }

    public void setVaccination(Vaccination vaccination) {
        this.vaccination = vaccination;
    }
}
