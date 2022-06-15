package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team5.dto.VaccinationCenterDTO;
import team5.model.Timeslot;
import team5.model.Vaccination;
import team5.model.VaccinationCenter;
import team5.services.VaccinationCenterService;

import java.util.List;

@RestController
public class VaccinationCenterController {

    private final VaccinationCenterService vaccinationCenterService;

    @Autowired
    public VaccinationCenterController(VaccinationCenterService vaccinationCenterService) {
        this.vaccinationCenterService = vaccinationCenterService;
    }

    @PostMapping("/vaccinationCenters")
    public VaccinationCenter createVaccinationCenter(@RequestBody VaccinationCenterDTO vaccinationCenterDTO) {
        return vaccinationCenterService.createVaccinationCenter(vaccinationCenterDTO);
    }

    @GetMapping("/vaccinationCenters")
    public List<VaccinationCenter> getAllVaccinationCenter() {
        return vaccinationCenterService.getAllVaccinationCenters();
    }

    @PostMapping("/vaccinationCenters/{vaccCode}")
    public void addTimeslotToVaccinationCenter(@PathVariable String vaccCode,
                                               @RequestParam long timeslotId) {
        vaccinationCenterService.addTimeslotToVaccinationCenter(vaccCode, timeslotId);
    }

    @DeleteMapping("/vaccinationCenters/{vaccCode}")
    public void deleteVaccinationCenter(@PathVariable String vaccCode) {
        vaccinationCenterService.deleteVaccinationCenter(vaccCode);
    }
    //ola ta timeslot twn emvoliastikwn me ta restriction ta dika mou
    @GetMapping("/vaccinationCenters/{vaccCode}/timeslots/{date}")
    public List<Timeslot> getFreeTimeSlotsByDateByVaccinationCenter(@PathVariable(value = "vaccCode") String vaccCode,
                                                                    @PathVariable(value = "date") String date) {
        return vaccinationCenterService.getFreeTimeSlotsByDateByVaccinationCenter(vaccCode, date);
    }

    @GetMapping("/vaccinationCenters/{vaccCode}/timeslots/month/{date}")
    public List<Timeslot> getFreeTimeSlotsInSameMonthByVaccinationCenter(@PathVariable(value = "vaccCode") String vaccCode,
                                                                         @PathVariable(value = "date") String date) {

        return vaccinationCenterService.getFreeTimeSlotsInSameMonthByVaccinationCenter(vaccCode, date);
    }


}
