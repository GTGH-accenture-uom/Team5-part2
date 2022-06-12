package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.model.Timeslot;
import team5.model.VaccinationCenter;
import team5.utilities.Conversion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TimeslotService {
    private final List<Timeslot> allTimeslots = new ArrayList<>();

    private VaccinationCenterService vaccinationCenterService;

    @Autowired
    public TimeslotService(VaccinationCenterService vaccinationCenterService) {
        this.vaccinationCenterService = vaccinationCenterService;
    }


    public long createTimeslot(LocalDateTime startDateTime, int duration) {
        Timeslot timeslot = new Timeslot(startDateTime, duration);
        System.out.println("Timeslot with id: " + timeslot.getId() + " created: " + timeslot);
        allTimeslots.add(timeslot);
        return timeslot.getId();
    }

    public Timeslot findTimeslotById(int id) {
        Timeslot foundTimeslot = null;
        Optional<Timeslot> timeslot;
        for (Timeslot t : allTimeslots) {
            if (t.getId() == id) {
                foundTimeslot = t;
            }
        }
        return foundTimeslot;
    }

    public List<Timeslot> findTimeslotsByDate(String date) {
        LocalDate localDate = Conversion.stringToLocalDate(date);
        List<Timeslot> timeslots = new ArrayList<>();
        for (Timeslot t : allTimeslots) {
            if (t.isAvailable() && ((t.getStartDateTime().toLocalDate().equals(localDate)))) {
                timeslots.add(t);
            }
        }
        return timeslots;
    }

    public List<Timeslot> getFreeTimeslotsByVaccinationCenter(String code) {

        VaccinationCenter foundVaccinationCenter = vaccinationCenterService.findVaccinationCenterByCode(code);
        return foundVaccinationCenter.getTimeslots()
                .stream()
                .filter(Timeslot::isAvailable)
                .collect(Collectors.toList());
        /*
        List<Timeslot> freeTimeslots = new ArrayList<>();
        for (Timeslot ts : vaccinationCenter.getTimeslots()) {
            if (ts.isAvailable()) {
                freeTimeslots.add(ts);
            }
        }
        return freeTimeslots;

         */
    }

    public List<Timeslot> getFreeTimeSlotsByDateByVaccinationCenter(String code, String date) {

        LocalDate localDate = Conversion.stringToLocalDate(date);
        VaccinationCenter foundVaccinationCenter = vaccinationCenterService.findVaccinationCenterByCode(code);
        return foundVaccinationCenter.getTimeslots()
                .stream()
                .filter(Timeslot::isAvailable)
                .filter(timeslot -> timeslot.getStartDateTime()
                        .toLocalDate()
                        .format(Conversion.pattern)
                        .equals(localDate.format(Conversion.pattern))).collect(Collectors.toList());
    }

    public List<Timeslot> getFreeTimeSlotsInSameMonthByVaccinationCenter(String code, String date) {
        LocalDate localDate = Conversion.stringToLocalDate(date);
        VaccinationCenter foundVaccinationCenter = vaccinationCenterService.findVaccinationCenterByCode(code);
        return foundVaccinationCenter.getTimeslots()
                .stream()
                .filter(Timeslot::isAvailable)
                .filter(timeslot -> isInSameMonth(localDate, timeslot.getStartDateTime().toLocalDate()))
                .collect(Collectors.toList());
    }

    private boolean isInSameMonth(LocalDate date, LocalDate timeslotDate) {
        boolean isInSameMonth = false;
        int givenMonthOfYear = date.get(ChronoField.MONTH_OF_YEAR);
        int givenMonthOfTimeslot = timeslotDate.get(ChronoField.MONTH_OF_YEAR);
        if (date.getYear() == timeslotDate.getYear()) {
            isInSameMonth = givenMonthOfYear == givenMonthOfTimeslot;
        }
        return isInSameMonth;
    }


    public List<Timeslot> getAllTimeslots() {
        return allTimeslots;
    }
}