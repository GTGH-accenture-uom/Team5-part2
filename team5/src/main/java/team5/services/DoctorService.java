package team5.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import team5.dto.DoctorDTO;
import team5.exceptions.*;
import team5.model.*;
import team5.utilities.InputValidator;
import team5.utilities.MessagesForExistingValues;

import java.util.ArrayList;
import java.util.List;


@Service
public class DoctorService {

    private final List<Doctor> allDoctors = new ArrayList<>();

    private final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    //First method create doctor to be used by the controller
    public Doctor createDoctor(DoctorDTO doctorDTO) {
        if (findDocByAmka(doctorDTO.getAmka()) == null) {
            if (InputValidator.checkAmka(doctorDTO.getAmka())) {
                Doctor doctor = createDoctor(doctorDTO.getAmka(), doctorDTO.getName(), doctorDTO.getSurname());
                allDoctors.add(doctor);
                logger.info("Doctor Created " + doctor);
                return doctor;
            } else {
                throw new CheckYourDataException();
            }
        }
        throw new ExistingRecordException(MessagesForExistingValues.DOCTOR_ALREADY_EXISTS.getErrorMessage());
    }


    public Doctor createDoctor(String amka, String firstName, String lastName) {
        Doctor doc = null;
        if (allDoctors.stream().noneMatch(doctor -> doctor.getAmka().equals(amka))) {
            if (InputValidator.checkAmka(amka)) {
                doc = new Doctor(amka, firstName, lastName);
                logger.info("Doctor Created " + doc);
                allDoctors.add(doc);
            } else {
                logger.warn("Check your input amka is defined with 11 digits ");
            }
        } else {
            logger.warn("This doctor already exists with amka " + amka);
        }
        return doc;
    }

    public Doctor findDoctorByAmka(String amka) {
        return allDoctors.stream()
                .filter(e -> e.getAmka()
                        .equals(amka))
                .findFirst().orElseThrow(() -> new DoctorNotFoundException(amka));
    }

    public Doctor findDocByAmka(String amka) {
        return allDoctors.stream()
                .filter(e -> e.getAmka()
                        .equals(amka))
                .findFirst().orElse(null);
    }


    public Doctor updateDoctor(String amka, DoctorDTO doctorDTO) {
        Doctor doctor = findDoctorByAmka(amka);
        if (doctor != null) {
            if (InputValidator.checkAmka(doctorDTO.getAmka())) {
                logger.info("Doctor before update " + doctor);
                doctor.setName(doctorDTO.getName());
                doctor.setAmka(doctorDTO.getAmka());
                doctor.setSurname(doctorDTO.getSurname());
                logger.info("Doctor after update " + doctor);
                return doctor;
            } else {
                throw new CheckYourDataException();
            }
        } else {
            throw new DoctorNotFoundException(amka);
        }
    }

    public void deleteDoctor(String amka) {
        if (allDoctors.removeIf(doctor -> doctor.getAmka().equals(amka))) {
            logger.info("Doctor with amka " + amka + " deleted");
        } else {
            throw new DoctorNotFoundException(amka);
        }
    }

    public void addTimeslotToDoctor(String amka, Timeslot timeslot) {
        Doctor doctor = findDocByAmka(amka);
        if (doctor != null && timeslot != null && timeslot.isAvailable() && !doctor.getTimeslots().contains(timeslot)) {
            doctor.addTimeslot(timeslot);
        } else if (timeslot != null) {
            logger.warn("Cannot add this timeslot with id " + timeslot.getId() + " with amka " + amka);
        }
    }


    public List<Doctor> getAllDoctors() {
        return allDoctors;
    }

}
