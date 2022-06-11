package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team5.model.Doctor;
import team5.model.Insured;
import team5.services.DoctorService;
import team5.services.InsuredService;

import java.util.List;

@RestController
public class DoctorController {


    private final DoctorService doctorService;


    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(path="/doctor")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }


}
