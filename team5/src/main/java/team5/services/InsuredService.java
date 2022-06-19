package team5.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import team5.config.SyntheticData;
import team5.dto.InsuredDTO;
import team5.dto.VaccinationWithStateDTO;
import team5.exceptions.CheckYourDataException;
import team5.exceptions.ExistingRecordException;
import team5.exceptions.InsuredNotFoundException;
import team5.exceptions.TimeslotNotFoundException;
import team5.model.*;
import org.springframework.stereotype.Service;
import team5.utilities.InputValidator;
import team5.utilities.MessagesForExistingValues;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class InsuredService {

    private final List<Insured> allInsured = new ArrayList<>();

    private final Logger logger = LoggerFactory.getLogger(InsuredService.class);


    //First method create Insured to be used from the controller
    public Insured createInsured(InsuredDTO insuredDTO) {
        if (findInsuredByAmka(insuredDTO.getAmka()) == null) {
            if (InputValidator.checkAfm(insuredDTO.getAfm()) && InputValidator.checkAmka(insuredDTO.getAmka())) {
                Insured insured = new Insured(insuredDTO.getAfm(), insuredDTO.getAmka(), insuredDTO.getName(),
                        insuredDTO.getSurname(), insuredDTO.getBirthdate(), insuredDTO.getEmail());
                allInsured.add(insured);
                return insured;
            } else {
                throw new CheckYourDataException();
            }
        } else {
            throw new ExistingRecordException(MessagesForExistingValues.INSURED_ALREADY_EXISTS.name());
        }
    }

    public Insured createInsured(String afm, String amka, String name, LocalDate birthdate, String surname, String email) {
        Insured insured = null;
        if (allInsured.stream().noneMatch(ins -> ins.getAmka().equals(amka))) {
            if (InputValidator.checkAfm(afm) && InputValidator.checkAmka(amka)) {
                insured = new Insured(afm, amka, name, surname, birthdate, email);
                logger.info("Insured Created " + insured);
                allInsured.add(insured);
            } else {
                logger.warn("->" + MessagesForExistingValues.INSURED_ALREADY_EXISTS);
            }
        }
        return insured;
    }

    //First find Insured method to be used from the controller
    public Insured findInsuredByAmka(String amka) {
        return allInsured.stream().filter(e -> e.getAmka().equals(amka))
                .findFirst()
                .orElseThrow(() -> new InsuredNotFoundException(amka));
    }

    //Second method of find insured not to be used from the controllers
    public Insured findInsByAmka(String amka) {
        try {
            return allInsured
                    .stream()
                    .filter(insured -> insured.getAmka().equals(amka)).findFirst()
                    .orElseThrow(() -> new InsuredNotFoundException(amka));
        } catch (InsuredNotFoundException insuredNotFoundException) {
            System.err.println(insuredNotFoundException.getMessage());
        }
        return null;
    }


    public Insured updateInsured(String amka, InsuredDTO insuredDTO) {
        Insured insured = findInsByAmka(amka);
        insured.setName(insuredDTO.getName());
        insured.setSurname(insuredDTO.getSurname());
        insured.setEmail(insuredDTO.getEmail());
        insured.setAmka(insuredDTO.getAmka());
        insured.setAfm(insuredDTO.getAfm());
        insured.setBirthdate(insuredDTO.getBirthdate());
        return insured;
    }

    public void deleteInsured(String amka) {
        if (allInsured.removeIf(insured -> insured.getAmka().equals(amka))) {
            System.out.println("Insured with amka " + amka + " deleted");
        } else {
            throw new InsuredNotFoundException(amka);
        }
    }


    public List<Insured> getAllInsured() {
        return allInsured;
    }


}
