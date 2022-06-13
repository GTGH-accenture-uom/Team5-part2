package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team5.model.Timeslot;
import team5.model.Vaccination;
import team5.services.TimeslotService;
import team5.services.VaccinationService;

import java.util.List;

@RestController
public class VaccinationController {

    private final VaccinationService vaccinationService;


    @Autowired
    public VaccinationController (VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @GetMapping("/vaccinations/state/{amka}")
    public List<Vaccination> getRecentVaccinationsByInsured(@PathVariable(value = "amka") String amka) {
        return vaccinationService.getRecentVaccinationsByInsured(amka);
    }


    @PostMapping("/doctors/vaccinations/vaccination/doctors/{doctorAmka}")
    public Vaccination createVaccination(@RequestBody Vaccination vaccination,
                                         @PathVariable(value = "doctorAmka")String doctorAmka){

        return vaccinationService.createVaccination(vaccination,doctorAmka);
    }


}
