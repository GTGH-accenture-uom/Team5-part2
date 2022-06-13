package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team5.model.Reservation;
import team5.services.ReservationService;

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
    public Reservation updateReservation(@PathVariable(value = "resevationId") String reservationId,
                                         @RequestParam(value = "timeslotId") String timeslotId) {

        return reservationService.updateReservation(reservationId, timeslotId);

    }
}

