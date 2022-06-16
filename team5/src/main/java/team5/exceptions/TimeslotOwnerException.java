package team5.exceptions;

public class TimeslotOwnerException extends RuntimeException {

    public TimeslotOwnerException(String amka) {
        super("Doctor with " + amka + " may not be the owner of this timeslot");
    }
}


