package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team5.dto.VaccinationDTO;
import team5.dto.VaccinationWithStateDTO;
import team5.model.Vaccination;
import team5.services.VaccinationService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class VaccinationController {

    private final VaccinationService vaccinationService;
    
    @Autowired
    public VaccinationController (VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @GetMapping("/vaccinations/state/{amka}")
    public List<VaccinationWithStateDTO> getAllRecentVaccinationsWithStatus(@PathVariable(value = "amka") String amka) {
        return vaccinationService.findAllRecentVaccinationsWithStatus(amka);
    }

    @PostMapping("/doctors/vaccinations/vaccination/doctors/{doctorAmka}")
    public Vaccination createVaccination(@RequestBody VaccinationDTO vaccinationDTO,
                                         @PathVariable(value = "doctorAmka")String doctorAmka){
        return vaccinationService.createVaccination(vaccinationDTO,doctorAmka);
    }

    @GetMapping("/vaccination/state/generateQRCode/brand/{vacc_brand}/{insuredAmka}")
    public byte [] generateQRCode(@PathVariable String insuredAmka
            ,@PathVariable String vacc_brand, HttpServletResponse response)  {
       return vaccinationService.generateQRCode(vacc_brand,insuredAmka,response);
    }


}
