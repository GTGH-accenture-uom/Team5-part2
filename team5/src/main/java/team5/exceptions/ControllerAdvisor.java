package team5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(value = {VaccinationCenterNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleVaccinationCenterNotFoundException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DoctorNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleDoctorNotFoundException(DoctorNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DateNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleDateNotFoundException(DateNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}
