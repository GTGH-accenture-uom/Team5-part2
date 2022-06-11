package team5.services;

import org.springframework.stereotype.Service;
import team5.model.Timeslot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TimeslotService {
    private final List<Timeslot> allTimeslots = new ArrayList<>();


    public long createTimeslot(LocalDateTime startDateTime, int duration){
        Timeslot timeslot = new Timeslot(startDateTime, duration);
        System.out.println("Timeslot with id: " + timeslot.getId() + " created: " + timeslot);
        allTimeslots.add(timeslot);
        return timeslot.getId();
    }

    public Timeslot findTimeslotById(int id) {
        Timeslot foundTimeslot = null;
        Optional<Timeslot> timeslot;
        for (Timeslot t: allTimeslots){
            if (t.getId()==id){
                foundTimeslot = t;
            }
        }
        return foundTimeslot;
    }

    public List<Timeslot> findTimeslotsByDate(LocalDate localDate){
        List<Timeslot> timeslots = new ArrayList<>();
        for (Timeslot t: allTimeslots){
            if (t.isAvailable() && ((t.getStartDateTime().toLocalDate().equals(localDate)))){
                timeslots.add(t);
            }
        }
        return timeslots;
    }

    public List<Timeslot> getAllTimeslots() {
        return allTimeslots;
    }
}
