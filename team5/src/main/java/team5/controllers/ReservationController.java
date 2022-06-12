package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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


        private final VaccinationCenterService vaccinationCenterService;

        @Autowired
        public ReservationController(VaccinationCenterService vaccinationCenterService){
            this.vaccinationCenterService = vaccinationCenterService;
        }

        @PostMapping("/reservation")
        public long createReservation(@RequestParam(value = "amkaInsured") String amkaInsured,
                                             @RequestParam(value = "timeslot") String date,
                                             //timeslot in db = datetime per doctor per vaccination center
                                             //timeslot in front = datetime only
                                             @RequestParam(value = "amkaDoctor") String amkaDoctor){
            return vaccinationCenterService.createReservation(amkaInsured, date, amkaDoctor);
        }
}

