package team5.services;

import org.springframework.stereotype.Service;
import team5.dto.VaccinationCenterDTO;
import team5.exceptions.ExistingRecordException;
import team5.exceptions.VaccinationCenterNotFoundException;
import team5.model.*;
import team5.utilities.DateUtils;
import team5.utilities.MessagesForExistingValues;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VaccinationCenterService {


    private final List<VaccinationCenter> allVaccinationCenters = new ArrayList<>();

    private final TimeslotService timeslotService;

    public VaccinationCenterService(TimeslotService timeslotService) {
        this.timeslotService = timeslotService;
    }

    public VaccinationCenter createVaccinationCenter(VaccinationCenterDTO vaccinationCenterDTO) {
        VaccinationCenter vaccinationCenter = new VaccinationCenter(vaccinationCenterDTO.getCode()
                , vaccinationCenterDTO.getCity(), vaccinationCenterDTO.getAddress());
        if (!allVaccinationCenters.contains(vaccinationCenter)) {
            allVaccinationCenters.add(vaccinationCenter);
        } else {
            throw new ExistingRecordException(MessagesForExistingValues.VACCINATION_CENTER_EXISTS.getErrorMessage());
        }
        return vaccinationCenter;
    }

    //Second method to create VaccinationCenter not to be used from the controller
    public VaccinationCenter createVaccinationCenter(String code, String city, String address) {
        VaccinationCenter vaccinationCenter = new VaccinationCenter(code, city, address);
        if (!allVaccinationCenters.contains(vaccinationCenter)) {
            allVaccinationCenters.add(vaccinationCenter);
        } else {
            System.err.println("This vaccination center with code " + code + " already exists");
        }
        return vaccinationCenter;
    }

    //we need to apply restrictions to this
    public void addTimeslotsToVaccinationCenter(List<Timeslot> timeslots, VaccinationCenter vaccinationCenter) {
        for (Timeslot t : timeslots) {
            if (!vaccinationCenter.getTimeslots().contains(t)) {
                vaccinationCenter.addTimeSlot(t);
            }
        }
    }

    public Timeslot findTimeslotByVaccinationCenter(String code, long timeslotId) {
        VaccinationCenter foundVaccinationCenter = findVaccinationCenterByCode(code);
        return foundVaccinationCenter
                .getTimeslots()
                .stream()
                .filter(e -> e.getId() == timeslotId).findFirst()
                .orElse(null);
    }


    public List<Timeslot> findFreeTimeSlotsByDateByVaccinationCenter(String code, String date) {
        LocalDate stringToLocalDate = DateUtils.stringToLocalDate(date);
        VaccinationCenter foundVaccinationCenter = findVaccinationCenterByCode(code);
        return foundVaccinationCenter
                .getTimeslots()
                .stream()
                .filter(Timeslot::isAvailable)
                .filter(timeslot -> timeslot.getStartDateTime().toLocalDate()
                        .equals(stringToLocalDate)).collect(Collectors.toList());
    }

    public List<Timeslot> findFreeTimeSlotsInSameMonthByVaccinationCenter(String code, String date) {
        LocalDate stringToLocalDate = DateUtils.stringToLocalDate(date);
        VaccinationCenter foundVaccinationCenter = findVaccinationCenterByCode(code);
        return foundVaccinationCenter.getTimeslots()
                .stream()
                .filter(Timeslot::isAvailable)
                .filter(timeslot -> (DateUtils.isTimeSlotAfterOrEqualsTheGivenDate(stringToLocalDate, timeslot)))
                .filter(timeslot -> DateUtils.areTimeslotsInSameMonth(stringToLocalDate, timeslot.getStartDateTime().toLocalDate()))
                .collect(Collectors.toList());
    }


    public void addTimeslotToVaccinationCenter(String vacc_code, long timeslotId) {
        VaccinationCenter foundVaccinationCenter = findVaccinationCenterByCode(vacc_code);
        Timeslot foundTimeslot = timeslotService.findTimeslotById(timeslotId);
        if (findTimeslotByVaccinationCenter(vacc_code, timeslotId) == null) {
            foundVaccinationCenter.addTimeSlot(foundTimeslot);
        }
        System.out.println("Timeslot added to vaccination center with code " + vacc_code);
    }

    public void deleteVaccinationCenter(String vacc_code) {
        if (allVaccinationCenters.removeIf(vaccinationCenter -> vaccinationCenter.getCode().equals(vacc_code))) {
            System.out.println("VaccinationCenter removed");
        } else {
            throw new VaccinationCenterNotFoundException(vacc_code);
        }

    }

    public VaccinationCenter findVaccinationCenterByCode(String code) {
        VaccinationCenter foundVaccinationCenter;
        Optional<VaccinationCenter> optionalVaccinationCenter = allVaccinationCenters
                .stream().filter(vaccCent -> vaccCent.getCode().equals(code)).findFirst();
        if (optionalVaccinationCenter.isPresent()) {
            foundVaccinationCenter = optionalVaccinationCenter.get();
        } else {
            throw new VaccinationCenterNotFoundException(code);
        }
        return foundVaccinationCenter;
    }
    public void printFreeTimeslotsByVaccinationCenter(String code) {
        System.out.println("------------------Vaccination center with code--------------" + code);
        findVaccinationCenterByCode(code).getTimeslots()
                .stream()
                .filter(Timeslot::isAvailable)
                .forEach(System.out::println);
    }

    public List<VaccinationCenter> getAllVaccinationCenters() {
        return allVaccinationCenters;
    }



}