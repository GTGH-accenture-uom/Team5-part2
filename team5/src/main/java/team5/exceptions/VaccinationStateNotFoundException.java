package team5.exceptions;

public class VaccinationStateNotFoundException extends RuntimeException {

    public VaccinationStateNotFoundException(String vacc_type, String amka) {
        super("Could not find last vaccination state for vacc_type " + vacc_type + " for insured with amka " + amka);
    }

}
