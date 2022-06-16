package team5.exceptions;

public class VaccinationStateNotFoundException extends RuntimeException {

    public VaccinationStateNotFoundException(String vacc_brand,String amka) {
        super("Could not find last vaccination state by vacc_brand " + vacc_brand + " for insured with amka " + amka);
    }

}
