package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/timeslots")
    public List<Timeslot> getAllTimeslots() {
        return timeslotService.getAllTimeslots();
    }

    @GetMapping ("/timeslots/date")
    public List<Timeslot> findTimeslotsByDate(@RequestParam(value="date") String date){
        return timeslotService.findTimeslotsByDate(date);

    }

    @GetMapping("/vaccinationCenters/{vaccCode}/{date}")
    public List<Timeslot> getFreeTimeSlotsByDateByVaccinationCenter(@PathVariable(value = "vaccCode") String vaccCode,
                                                                    @PathVariable(value = "date") String date) {
        return timeslotService.getFreeTimeSlotsByDateByVaccinationCenter(vaccCode, date);
    }


    @GetMapping("/vaccinationCenters/{vaccCode}/month/{date}")
    public List<Timeslot> getFreeTimeSlotsInSameMonthByVaccinationCenter(@PathVariable(value = "vaccCode") String vaccCode,
                                                                         @PathVariable(value = "date") String date) {

        return timeslotService.getFreeTimeSlotsInSameMonthByVaccinationCenter(vaccCode, date);
    }


}
