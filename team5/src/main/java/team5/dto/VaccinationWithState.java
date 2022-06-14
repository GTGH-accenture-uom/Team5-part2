package team5.dto;

import team5.model.Vaccination;
import team5.utilities.VaccinationState;

public class VaccinationWithState {

    private static VaccinationState totalVaccinationState;
    private Vaccination vaccination;
    private VaccinationState vaccinationState;

    public VaccinationWithState(Vaccination vaccination, VaccinationState vaccinationState) {
        this.vaccination = vaccination;
        this.vaccinationState = vaccinationState;
    }

    public static VaccinationState getTotalVaccinationState() {
        return totalVaccinationState;
    }

    public static void setTotalVaccinationState(VaccinationState totalVaccinationState) {
        VaccinationWithState.totalVaccinationState = totalVaccinationState;
    }

    public Vaccination getVaccination() {
        return vaccination;
    }

    public void setVaccination(Vaccination vaccination) {
        this.vaccination = vaccination;
    }

    public VaccinationState getVaccinationState() {
        return vaccinationState;
    }

    public void setVaccinationState(VaccinationState vaccinationState) {
        this.vaccinationState = vaccinationState;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VaccinationWithState{");
        sb.append("vaccination=").append(vaccination);
        sb.append(", vaccinationState=").append(vaccinationState);
        sb.append('}');
        return sb.toString();
    }
}
