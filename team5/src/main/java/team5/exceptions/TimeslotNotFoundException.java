package team5.exceptions;

public class TimeslotNotFoundException extends RuntimeException {

    public TimeslotNotFoundException(long id) {
        super("Timeslot with id " + id + " is not found");
    }
}
