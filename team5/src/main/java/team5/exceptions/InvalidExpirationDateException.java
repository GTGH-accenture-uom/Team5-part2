package team5.exceptions;

import java.time.LocalDateTime;

public class InvalidExpirationDateException extends RuntimeException {

    public InvalidExpirationDateException(LocalDateTime localDateTime) {
        super("Expiration date should be after " + localDateTime);
    }
}
