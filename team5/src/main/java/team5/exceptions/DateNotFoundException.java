package team5.exceptions;

public class DateNotFoundException extends RuntimeException {


    public DateNotFoundException(String date) {
        super("Could not find date " + date + " the right format is (date-month-year)");
    }

}
