package team5.exceptions;

public class InsuredNotFoundException extends RuntimeException{

    public InsuredNotFoundException(String amka) {
        super("Could not find insured with amka " + amka);
    }
}
