package team5.dto;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class TimeslotDTO {

    private static final AtomicLong count = new AtomicLong(0);
    private long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean available;
    private int duration;


    public long getId() {
        return id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
