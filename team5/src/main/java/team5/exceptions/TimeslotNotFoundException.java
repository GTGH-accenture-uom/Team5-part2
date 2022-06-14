package team5.exceptions;

public class TimeslotNotFoundException extends RuntimeException{

    public TimeslotNotFoundException(long id) {
        super("could not find timeslot with id " + id);
    }
}
