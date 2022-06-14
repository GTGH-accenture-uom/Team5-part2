package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.dto.TimeslotDTO;
import team5.exceptions.TimeslotNotFoundException;
import team5.model.Timeslot;
import team5.model.VaccinationCenter;
import team5.utilities.DateUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TimeslotService {
    private final List<Timeslot> allTimeslots = new ArrayList<>();

    private VaccinationCenterService vaccinationCenterService;

    @Autowired
    public TimeslotService(VaccinationCenterService vaccinationCenterService) {
        this.vaccinationCenterService = vaccinationCenterService;
    }


    public Timeslot createTimeslot(LocalDateTime startDateTime, int duration) {
        Timeslot timeslot = new Timeslot(startDateTime, duration);
        System.out.println("Timeslot with id: " + timeslot.getId() + " created: " + timeslot);
        allTimeslots.add(timeslot);
        return timeslot;
    }

    public Timeslot createTimeslot(TimeslotDTO timeslotDTO) {
        Timeslot timeslot = new Timeslot(timeslotDTO.getStartDateTime(), timeslotDTO.getDuration());
        allTimeslots.add(timeslot);
        return timeslot;
    }

    public Timeslot findTimeslotById(long id) {
        Timeslot foundTimeslot = null;
        for (Timeslot t : allTimeslots) {
            if (t.getId() == id) {
                foundTimeslot = t;
            }
        }
        if (foundTimeslot != null) {
            return foundTimeslot;
        }
        throw new TimeslotNotFoundException(id);
    }

    public List<Timeslot> findTimeslotsByDate(String date) {
        LocalDate localDate = DateUtils.stringToLocalDate(date);
        List<Timeslot> timeslots = new ArrayList<>();
        for (Timeslot t : allTimeslots) {
            if (t.isAvailable() && ((t.getStartDateTime().toLocalDate().equals(localDate)))) {
                timeslots.add(t);
            }
        }
        return timeslots;
    }

    public Timeslot updateTimeslot(long id, TimeslotDTO timeslotDTO) {
        Timeslot timeslot = findTimeslotById(id);
        if (timeslot != null) {
            timeslot.setStartDateTime(timeslotDTO.getStartDateTime());
            calcEndDateTime(timeslot, timeslotDTO.getStartDateTime(), timeslot.getDuration());
        } else {
            throw new TimeslotNotFoundException(id);
        }

        return timeslot;
    }

    public void deleteTimeslot(long id) {
        if (allTimeslots.removeIf(e -> e.getId() == id)) {
            System.out.println("removed");
        } else {
            throw new TimeslotNotFoundException(id);
        }
    }

    public List<Timeslot> getFreeTimeslotsByVaccinationCenter(String code) {
        VaccinationCenter foundVaccinationCenter = vaccinationCenterService.findVaccinationCenterByCode(code);
        return foundVaccinationCenter.getTimeslots()
                .stream()
                .filter(Timeslot::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Timeslot> getFreeTimeSlotsByDateByVaccinationCenter(String code, String date) {
        LocalDate stringToLocalDate = DateUtils.stringToLocalDate(date);
        VaccinationCenter foundVaccinationCenter = vaccinationCenterService.findVaccinationCenterByCode(code);
        return foundVaccinationCenter.getTimeslots()
                .stream()
                .filter(Timeslot::isAvailable)
                .filter(timeslot -> timeslot.getStartDateTime()
                        .toLocalDate()
                        .equals(stringToLocalDate)).collect(Collectors.toList());
    }

    public List<Timeslot> getFreeTimeSlotsInSameMonthByVaccinationCenter(String code, String date) {
        LocalDate stringToLocalDate = DateUtils.stringToLocalDate(date);
        VaccinationCenter foundVaccinationCenter = vaccinationCenterService.findVaccinationCenterByCode(code);
        return foundVaccinationCenter.getTimeslots()
                .stream()
                .filter(Timeslot::isAvailable)
                .filter(timeslot -> (DateUtils.isTimeSlotAfterOrEqualsTheGivenDate(stringToLocalDate, timeslot)))
                .filter(timeslot -> DateUtils.areTimeslotsInSameMonth(stringToLocalDate, timeslot.getStartDateTime().toLocalDate()))
                .collect(Collectors.toList());
    }

    public List<Timeslot> getTimeslotsByLocalDateTimeByDoctor(String amkaInsured, LocalDateTime localDateTime, String amkaDoctor) {
        List<Timeslot> timeslotsByDate = getTimeslotsByLocalDateTime(getAllTimeslots(), localDateTime);
        List<Timeslot> timeslotsByDoctorByDate = getTimeslotsByDoctor(timeslotsByDate, amkaDoctor);
        return timeslotsByDoctorByDate;
    }

    public List<Timeslot> getTimeslotsByLocalDateTime(List<Timeslot> timeslots, LocalDateTime localDateTime) {
        List<Timeslot> timeslotsByDate = new ArrayList<>();
        for (Timeslot t : timeslots) {
            if (t.getStartDateTime().equals(localDateTime)) {
                timeslotsByDate.add(t);
            }
        }
        return timeslotsByDate;
    }

    public List<Timeslot> getTimeslotsByDoctor(List<Timeslot> timeslots, String amka) {
        List<Timeslot> timeslotsByDoctor = new ArrayList<>();
        for (Timeslot t : timeslots) {
            if (t.getDoctor() != null && t.getDoctor().getAmka().equals(amka)) {
                timeslotsByDoctor.add(t);
            }
        }
        return timeslotsByDoctor;
    }


    public List<Timeslot> getAllTimeslots() {
        return allTimeslots;
    }

    public void calcEndDateTime(Timeslot timeslot, LocalDateTime startDateTime, int duration) {
        timeslot.setEndDateTime(startDateTime.plusMinutes(duration));
    }
}