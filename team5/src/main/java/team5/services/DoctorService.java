package team5.services;

import org.springframework.stereotype.Service;
import team5.dto.DoctorDTO;
import team5.exceptions.*;
import team5.model.*;
import team5.utilities.InputValidator;
import team5.utilities.MessagesForExistingValues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {


    private final List<Doctor> allDoctors = new ArrayList<>();

    public void createDoctor(String amka, String firstName, String lastName) {
        if (allDoctors.stream().noneMatch(doctor -> doctor.getAmka().equals(amka))) {
            if (InputValidator.checkAmka(amka)) {
                Doctor doctor = new Doctor(amka, firstName, lastName);
                System.out.println(doctor);
                allDoctors.add(doctor);
            }
        } else {
            System.err.println(MessagesForExistingValues.DOCTOR_ALREADY_EXISTS);
        }
    }


    //First method create Doctor to be used from the controller
    public Doctor createDoctor(DoctorDTO doctorDTO) {
        if (findDocByAmka(doctorDTO.getAmka()) == null) {
            if (InputValidator.checkAfm(doctorDTO.getAmka())) {
                Doctor doctor = new Doctor(doctorDTO.getAmka(), doctorDTO.getName(), doctorDTO.getSurname());
                allDoctors.add(doctor);
                return doctor;
            } else {
                throw new CheckYourDataException();
            }
        }
        throw new ExistingRecordException(MessagesForExistingValues.DOCTOR_ALREADY_EXISTS.getErrorMessage());
    }

    public Doctor findDoctorByAmka(String amka) {
        return allDoctors.stream().filter(e -> e.getAmka().equals(amka))
                .findFirst()
                .orElseThrow(() -> new DoctorNotFoundException(amka));
    }

    //Second method of find doctor not to be used from the controllers
    public Doctor findDocByAmka(String amka) {
        try {
            return allDoctors
                    .stream()
                    .filter(e -> e.getAmka().equals(amka)).findFirst()
                    .orElseThrow(() -> new DoctorNotFoundException(amka));
        } catch (DoctorNotFoundException doctorNotFoundException) {
            System.err.println(doctorNotFoundException.getMessage());
        }
        return null;
    }


    public Doctor updateDoctor(String amka, DoctorDTO doctorDTO) {
        Doctor doctor = findDoctorByAmka(amka);
        doctor.setName(doctorDTO.getName());
        doctor.setAmka(doctorDTO.getAmka());
        doctor.setSurname(doctorDTO.getSurname());
        return doctor;
    }

    public void deleteDoctor(String amka) {
        if (allDoctors.removeIf(doctor -> doctor.getAmka().equals(amka))) {
            System.out.println("Insured with amka " + amka + " deleted");
        } else {
            throw new DoctorNotFoundException(amka);
        }
    }

    public void addTimeslotToDoctor(String amka, Timeslot timeslot) {
        try {
            Doctor doctor = findDocByAmka(amka);
            if (doctor != null && timeslot != null && timeslot.isAvailable()) {
               // doctor.addTimeslot(timeslot);
                timeslot.setDoctor(doctor);
            } else {
                System.err.println("Timeslot can not be added");
            }
        } catch (DoctorNotFoundException doctorNotFoundException) {
            System.out.println(doctorNotFoundException.getMessage());
        }
    }


    public List<Doctor> getAllDoctors() {
        return allDoctors;
    }

}
