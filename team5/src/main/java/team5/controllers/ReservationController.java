package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team5.dto.InsuredDTO;
import team5.dto.ReservationDTO;
import team5.model.Insured;
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
    public long createReservationBody(@RequestBody ReservationDTO body) {
        return reservationService.createReservationBody(body);
        //timeslot in db = datetime per doctor per vaccination center
        //timeslot in front = datetime only
    }

    @GetMapping("/reservations/{id}")
    public Reservation findReservationById(@PathVariable long id){
        return  reservationService.findReservationById(id);
    }

    @GetMapping("/reservations/doctor/{amkaDoctor}")
    public List<Reservation> findReservationsByAllFilters(
            @PathVariable(value = "amkaDoctor") String amkaDoctor,
            @RequestParam(value = "amkaInsured", required=false) String amkaInsured,
            @RequestParam(value = "code", required=false) String code,
            @RequestParam(value = "date", required=false) String date){
        return reservationService.findReservationsByAllFilters(amkaInsured, amkaDoctor, code, date);
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

