package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team5.dto.DoctorDTO;
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

    @GetMapping(path = "/doctors")
    public List<Doctor> findAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.createDoctor(doctorDTO);
    }

    @GetMapping("/doctors/{amka}")
    public Doctor findDoctor(@PathVariable String amka) {
        return doctorService.findDoctorByAmka(amka);
    }

    @PutMapping("/doctors/{amka}")
    public Doctor updateDoctor(@PathVariable String amka, @RequestBody DoctorDTO doctorDTO) {
        return doctorService.updateDoctor(amka, doctorDTO);
    }

    @DeleteMapping("/doctors/{amka}")
    public void deleteDoctor(@PathVariable String amka) {
        doctorService.deleteDoctor(amka);
    }


}
