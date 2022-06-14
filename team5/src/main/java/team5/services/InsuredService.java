package team5.services;

import team5.config.SyntheticData;
import team5.dto.InsuredDTO;
import team5.model.Doctor;
import team5.model.VaccinationCenter;
import team5.model.Insured;
import team5.model.Reservation;
import org.springframework.stereotype.Service;
import team5.utilities.InputValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class InsuredService {

    private final List<Insured> allInsured = new ArrayList<>();

    public void createInsured(String afm, String amka, String name, LocalDate birthdate, String surname, String email) {
        if (findInsuredByAmka(amka) == null) {
            if (InputValidator.checkAfm(afm) && InputValidator.checkAmka(amka)) {
                Insured insured = new Insured(afm, amka, name, surname, birthdate, email);
                allInsured.add(insured);
                System.out.println("created insured: " + insured);
            }
        }
    }


    //Second method create Insured to be used from the api

    public Insured createInsured(InsuredDTO insuredDTO) {
        if (findInsuredByAmka(insuredDTO.getAmka()) != null) {
            if (InputValidator.checkAfm(insuredDTO.getAfm()) && InputValidator.checkAmka(insuredDTO.getAmka())) {
                Insured insured = new Insured(insuredDTO.getAfm(), insuredDTO.getAmka(), insuredDTO.getName()
                        , insuredDTO.getSurname(), insuredDTO.getBirthdate(), insuredDTO.getEmail());
                return insured;
            }
        }
        throw new RuntimeException(" Insured allready exists");

    }


    public List<Insured> getAllInsured() {
        return allInsured;
    }

    public Insured findInsuredByAmka(String amka) {
        Insured foundInsured = null;
        Optional<Insured> insured = allInsured.stream()
                .filter(e -> e.getAmka().equals(amka)).findFirst();
        if (insured.isPresent()) {
            foundInsured = insured.get();
        }
        return foundInsured;
    }
}
