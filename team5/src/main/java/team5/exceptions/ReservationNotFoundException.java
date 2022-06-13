package team5.exceptions;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException(long timeslotId) {
        super("Could not find reservation with timeslot id " + timeslotId);
    }

}
