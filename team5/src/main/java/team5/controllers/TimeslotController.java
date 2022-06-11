package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team5.model.Timeslot;
import team5.services.TimeslotService;

import java.util.List;

@RestController
public class TimeslotController {

    private final TimeslotService timeslotService;

    @Autowired
    public TimeslotController(TimeslotService timeslotService){
        this.timeslotService = timeslotService;
    }

    @GetMapping("/timeslots")
    public List<Timeslot> getAllTimeslots(){
        return timeslotService.getAllTimeslots();
    }
}
