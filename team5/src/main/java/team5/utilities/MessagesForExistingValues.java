package team5.utilities;

public enum MessagesForExistingValues {

    VACCINATION_ALREADY_MADE("This Vaccination has already been made");

    private String ErrorMessage;


    MessagesForExistingValues(String errorMessage) {
        this.ErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }
}
