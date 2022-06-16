package team5.exceptions;

public class DoctorNotAuthorizedException extends RuntimeException{
    public DoctorNotAuthorizedException(long timeslotId) {
        super("Doctor is not authorized to declare a vaccination with timeslots id " + timeslotId);
    }

}
