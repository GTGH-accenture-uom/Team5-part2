package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team5.model.Reservation;
import team5.model.Timeslot;
import team5.services.ReservationService;
import team5.services.TimeslotService;
import team5.services.VaccinationCenterService;
import team5.utilities.Conversion;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReservationController {



    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {

        this.reservationService = reservationService;
    }

    @PostMapping("/reservation")
    public long createReservation(@RequestParam(value = "amkaInsured") String amkaInsured, @RequestParam(value = "timeslot") String date,
                                  //timeslot in db = datetime per doctor per vaccination center
                                  //timeslot in front = datetime only
                                  @RequestParam(value = "amkaDoctor") String amkaDoctor) {
        return reservationService.createReservation(amkaInsured, date, amkaDoctor);
    }

    @PutMapping("/reservation/{resevationId}")
    public String updateReservation(@PathVariable(value = "resevationId") String reservationId,
                                    @RequestParam(value = "timeslotId") String timeslotId) {

        return reservationService.updateReservation(reservationId, timeslotId);
    }
}

