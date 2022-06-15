package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team5.dto.TimeslotDTO;
import team5.model.Timeslot;
import team5.services.TimeslotService;
import team5.services.VaccinationCenterService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TimeslotController {

    private final TimeslotService timeslotService;

    @Autowired
    public TimeslotController(TimeslotService timeslotService) {
        this.timeslotService = timeslotService;
    }

    @PostMapping("/timeslots")
    public Timeslot createTimeslot(@RequestBody TimeslotDTO timeslotDTO) {
        return timeslotService.createTimeslot(timeslotDTO);
    }

    @GetMapping("/timeslots/{id}")
    public Timeslot getTimeslot(@PathVariable long id) {
        return timeslotService.findTimeslotById(id);
    }

    @PutMapping("/timeslots/{id}")
    public Timeslot updateTimeslot(@PathVariable Long id, @RequestBody TimeslotDTO timeslotDTO) {
        return timeslotService.updateTimeslot(id, timeslotDTO);
    }

    @DeleteMapping("/timeslots/{id}")
    public void deleteTimeslot(@PathVariable long id) {
        timeslotService.deleteTimeslot(id);
    }

    @GetMapping("/timeslots")
    public List<Timeslot> getAllTimeslots() {
        return timeslotService.getAllTimeslots();
    }

    @GetMapping("/timeslots/date")
    public List<Timeslot> findTimeslotsByDate(@RequestParam(value = "date") String date) {
        return timeslotService.findTimeslotsByDate(date);
    }


}
