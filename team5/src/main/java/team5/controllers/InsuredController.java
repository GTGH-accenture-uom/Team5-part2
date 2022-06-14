package team5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team5.dto.InsuredDTO;
import team5.model.Insured;
import team5.services.InsuredService;
import java.util.List;

@RestController
public class InsuredController {


    private final InsuredService insuredService;


    @Autowired
    public InsuredController(InsuredService insuredService) {
        this.insuredService = insuredService;
    }

    @PostMapping("/allInsured")
    public Insured createInsured(@RequestBody InsuredDTO insuredDTO) {
        //return insuredService.createInsured(insuredDTO);
        return null;
    }


    @GetMapping(path="/allInsured")
    public List<Insured> getAllInsured() {
        return insuredService.getAllInsured();
    }


}
