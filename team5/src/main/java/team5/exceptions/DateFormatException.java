package team5.exceptions;

public class DateFormatException extends RuntimeException {


    public DateFormatException(String date) {
        super(date + " cannot be processed the right format is (date-month-year)");

    }

}
