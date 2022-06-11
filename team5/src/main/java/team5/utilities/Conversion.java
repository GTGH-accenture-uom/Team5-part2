package team5.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Conversion {
    public static LocalDate stringToLocalDate(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(input,format);

    }

}
