package team5.exceptions;

public class VaccinationStateNotFoundException extends RuntimeException {

    public VaccinationStateNotFoundException(String vacc_brand) {
        super("Could not find last vaccination state by vacc_brand " + vacc_brand);
    }

}
