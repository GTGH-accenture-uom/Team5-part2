package team5.services;

import team5.config.SyntheticData;
import team5.dto.InsuredDTO;
import team5.exceptions.CheckYourDataException;
import team5.exceptions.ExistingRecordException;
import team5.exceptions.InsuredNotFoundException;
import team5.exceptions.TimeslotNotFoundException;
import team5.model.Doctor;
import team5.model.VaccinationCenter;
import team5.model.Insured;
import team5.model.Reservation;
import org.springframework.stereotype.Service;
import team5.utilities.InputValidator;
import team5.utilities.MessagesForExistingValues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class InsuredService {

    private final List<Insured> allInsured = new ArrayList<>();

    //First method create insured not to be used from the controller
    public void createInsured(String afm, String amka, String name, LocalDate birthdate, String surname, String email) {
        if (allInsured.stream().noneMatch(insured -> insured.getAmka().equals(amka))) {
            if (InputValidator.checkAfm(afm) && InputValidator.checkAmka(amka)) {
                Insured insured = new Insured(afm, amka, name, surname, birthdate, email);
                System.out.println("Insured " + insured);
                allInsured.add(insured);
            }
        } else {
            System.err.println(MessagesForExistingValues.INSURED_ALREADY_EXISTS);
        }
    }

    //Second method create Insured to be used from the controller
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
        }
        throw new ExistingRecordException(MessagesForExistingValues.INSURED_ALREADY_EXISTS.getErrorMessage());
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
