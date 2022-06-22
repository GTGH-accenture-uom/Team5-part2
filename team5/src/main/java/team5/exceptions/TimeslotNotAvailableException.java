package team5.exceptions;

public class TimeslotNotAvailableException extends RuntimeException{

        public TimeslotNotAvailableException(long id) {
        super("Timeslot with id " + id + " is not available");
    }
}
