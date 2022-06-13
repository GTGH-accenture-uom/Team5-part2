package team5.exceptions;

public class ExistingRecordException extends RuntimeException {
    private String errorMessage;

    public ExistingRecordException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
