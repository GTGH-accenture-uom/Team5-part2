package team5.utilities;

import team5.exceptions.DateNotFoundException;
import team5.model.Timeslot;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class DateUtils {
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDate stringToLocalDate(String input) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(input, dateFormatter);
        } catch (DateTimeException dateTimeException) {
            throw new DateNotFoundException(input);
        }
        return localDate;

    }

    public static boolean areTimeslotsInSameMonth(LocalDate givenDate, LocalDate timeslotDate) {
        boolean isInSameMonth = false;
        int givenMonthOfYear = givenDate.getMonthValue();
        int givenMonthOfTimeslot = timeslotDate.getMonthValue();
        if (givenDate.getYear() == timeslotDate.getYear()) {
            isInSameMonth = givenMonthOfYear == givenMonthOfTimeslot;
        }
        return isInSameMonth;
    }

    public static boolean isTimeSlotAfterOrEqualsTheGivenDate(LocalDate givenDate, Timeslot timeslot) {
        return timeslot.getStartDateTime().toLocalDate().isEqual(givenDate)
                || (timeslot.getStartDateTime().toLocalDate().isAfter(givenDate));
    }

    public static LocalDateTime stringToLocalDateTime(String input) {

        return LocalDateTime.parse(input, dateTimeFormatter);

    }

}
