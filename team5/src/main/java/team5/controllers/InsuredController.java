package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team5.dto.InsuredDTO;
import team5.dto.TimeslotDTO;
import team5.model.Insured;
import team5.model.Timeslot;
import team5.services.InsuredService;

import java.util.List;

@RestController
public class InsuredController {


    private final InsuredService insuredService;


    @Autowired
    public InsuredController(InsuredService insuredService) {
        this.insuredService = insuredService;
    }

    @PostMapping("/insureds")
    public Insured createInsured(@RequestBody InsuredDTO insuredDTO) {
        return insuredService.createInsured(insuredDTO);
    }

    @GetMapping(path = "/insureds")
    public List<Insured> getAllInsured() {
        return insuredService.getAllInsured();
    }

    @GetMapping("/insureds/{amka}")
    public Insured getInsured(@PathVariable String amka) {
        return insuredService.findInsByAmka(amka);
    }

    @PutMapping("/insureds/{amka}")
    public Insured updateInsured(@PathVariable String amka, @RequestBody InsuredDTO insuredDTO) {
        return insuredService.updateInsured(amka, insuredDTO);
    }

    @DeleteMapping("/insureds/{amka}")
    public void deleteInsured(@PathVariable String amka) {
        insuredService.deleteInsured(amka);
    }

}
