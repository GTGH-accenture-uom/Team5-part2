package team5.exceptions;

public class ReservationCannotBeUpdated extends RuntimeException {

    public ReservationCannotBeUpdated(long id) {

        super("Cannot update Reservation with id " + id);
    }

}
