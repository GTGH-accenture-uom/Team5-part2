package team5.utilities;

import team5.exceptions.DateFormatException;
import team5.model.Timeslot;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static LocalDate stringToLocalDate(String input) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(input, dateFormatter);
        } catch (DateTimeException dateTimeException) {
            throw new DateFormatException(input);
        }
        return localDate;
    }

    public static boolean areTimeslotsInSameMonth(LocalDate givenDate, LocalDate timeslotDate) {
        boolean isInSameMonth = false;
        int givenMonthOfDate = givenDate.getMonthValue();
        int givenMonthOfTimeslot = timeslotDate.getMonthValue();
        if (givenDate.getYear() == timeslotDate.getYear()) {
            isInSameMonth = givenMonthOfDate == givenMonthOfTimeslot;
        }
        return isInSameMonth;
    }

    public static boolean isTimeSlotAfterOrEqualsTheGivenDate(LocalDate givenDate, Timeslot timeslot) {
        return timeslot.getStartDateTime().toLocalDate().isEqual(givenDate)
                || (timeslot.getStartDateTime().toLocalDate().isAfter(givenDate));
    }

    public static LocalDateTime stringToLocalDateTime(String input) {
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(input, dateTimeFormatter);
        } catch (DateTimeException dateTimeException) {
            throw new DateFormatException(input);
        }
        return localDateTime;

    }


}
