package team5.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

    private String amkaInsured;
    private String amkaDoctor;
    private LocalDateTime timeslot;

    public ReservationDTO(String amkaInsured, String amkaDoctor, LocalDateTime timeslot) {
        this.amkaInsured = amkaInsured;
        this.amkaDoctor = amkaDoctor;
        this.timeslot = timeslot;
    }

    public String getAmkaInsured() {
        return amkaInsured;
    }

    public void setAmkaInsured(String amkaInsured) {
        this.amkaInsured = amkaInsured;
    }
}
