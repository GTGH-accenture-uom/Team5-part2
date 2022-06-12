package team5.utilities;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {
    public static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static LocalDate stringToLocalDate(String input) {
        return LocalDate.parse(input,pattern);
    }

    public static LocalDateTime stringToLocalDateTime(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(input, format);

    }

}
