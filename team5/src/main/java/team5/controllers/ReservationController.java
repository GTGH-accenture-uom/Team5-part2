package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team5.model.Reservation;
import team5.services.ReservationService;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/reservations/{id}")
    public Reservation findReservationById(@PathVariable long id){
        return  reservationService.findReservationById(id);
    }

    @GetMapping("/reservations")
    public List<Reservation> findReservationsByAllFilters(
            @RequestParam(value = "amkaInsured", required=false) String amkaInsured,
            @RequestParam(value = "amkaDoctor", required=false) String amkaDoctor){
        return reservationService.findReservationsByAllFilters(amkaInsured, amkaDoctor);
    }


    @PutMapping("/reservation/{resevationId}")
    public Reservation updateReservation(@PathVariable(value = "resevationId") long reservationId,
                                         @RequestParam(value = "timeslotId") long timeslotId) {
        return reservationService.updateReservation(reservationId, timeslotId);
    }


    @DeleteMapping("/reservations/{id}")
    public void deleteReservation(@PathVariable long id){
        reservationService.deleteReservation(id);
    }

}

