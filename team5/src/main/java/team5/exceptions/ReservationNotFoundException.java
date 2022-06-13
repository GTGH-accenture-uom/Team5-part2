package team5.exceptions;

public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(long id) {
        super("Could not find reservation with timeslot id " + id);
    }

}
