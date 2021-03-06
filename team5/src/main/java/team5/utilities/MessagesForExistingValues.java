package team5.utilities;

public enum MessagesForExistingValues {

    VACCINATION_ALREADY_MADE("This Vaccination has already been made"),
    INSURED_ALREADY_EXISTS("This insured already exists with amka"),
    DOCTOR_ALREADY_EXISTS("This doctor already exists"),
    VACCINATION_CENTER_EXISTS("This vaccination center already exists");

    private String ErrorMessage;

    public String getErrorMessage() {
        return ErrorMessage;
    }

    MessagesForExistingValues(String errorMessage) {
        this.ErrorMessage = errorMessage;
    }
}
