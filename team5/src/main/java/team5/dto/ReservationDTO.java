package team5.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

    private String amkaInsured;
    private String amkaDoctor;
    private String timeslot;

    public ReservationDTO(String amkaInsured, String timeslot, String amkaDoctor) {
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

    public String getAmkaDoctor() {
        return amkaDoctor;
    }

    public void setAmkaDoctor(String amkaDoctor) {
        this.amkaDoctor = amkaDoctor;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }
}
