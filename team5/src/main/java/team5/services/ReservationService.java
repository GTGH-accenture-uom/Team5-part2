package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.model.Timeslot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private TimeslotService timeslotService;

    @Autowired
    public ReservationService(TimeslotService timeslotService) {this.timeslotService = timeslotService;}

    public List<Timeslot> getTimeslotsByLocalDateTimeByDoctor(String amkaInsured, LocalDateTime localDateTime, String amkaDoctor){
        List<Timeslot> timeslots = timeslotService.getAllTimeslots();
        List<Timeslot> timeslotsByDate = getTimeslotsByLocalDateTime(timeslots, localDateTime);
        List<Timeslot> timeslotsByDoctorByDate = getTimeslotsByDoctor(timeslotsByDate, amkaDoctor);
        return timeslotsByDoctorByDate;
    }

    public List<Timeslot> getTimeslotsByLocalDateTime(List<Timeslot> timeslots, LocalDateTime localDateTime){
        timeslots = timeslotService.getAllTimeslots();
        List<Timeslot> timeslotsByDate = new ArrayList<>();
        for (Timeslot t : timeslots){
            if (t.getStartDateTime().equals(localDateTime)){
                timeslotsByDate.add(t);
            }
        }
        return timeslotsByDate;
    }

    public List<Timeslot> getTimeslotsByDoctor(List<Timeslot> timeslots, String amka){
        timeslots = timeslotService.getAllTimeslots();
        List<Timeslot> timeslotsByDoctor = new ArrayList<>();
        for (Timeslot t : timeslots){
            if (t.getDoctor()!=null && t.getDoctor().getAmka().equals(amka)){
                timeslotsByDoctor.add(t);
            }
        }
        return timeslotsByDoctor;
    }



}

