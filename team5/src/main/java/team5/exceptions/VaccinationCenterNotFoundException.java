package team5.exceptions;

public class VaccinationCenterNotFoundException extends RuntimeException {

    public VaccinationCenterNotFoundException(String vaccCode) {
        super("Could not find vaccination center with code " + vaccCode);
    }

}

