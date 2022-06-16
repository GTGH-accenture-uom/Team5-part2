package team5.exceptions;

public class ReservationCannotBeUpdated extends RuntimeException {

    public ReservationCannotBeUpdated(long id) {
        super("Cannot update Reservation with id " + id + ".You are not allowed to change the reservation more than 2 times");
    }

}
