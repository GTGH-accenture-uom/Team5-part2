package team5.dto;

import team5.model.Vaccination;
import team5.services.VaccinationService;
import team5.utilities.VaccinationState;

import java.time.LocalDateTime;

public class VaccinationWithStateDTO {

    private Vaccination vaccination;
    private VaccinationState vaccinationState;
    private LocalDateTime lastUpdated;

    public VaccinationWithStateDTO(Vaccination vaccination) {
        this.vaccination = vaccination;
        this.vaccinationState = getVaccinationStatus(vaccination);
        this.lastUpdated = LocalDateTime.now();
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

    public VaccinationState getVaccinationStatus(Vaccination vaccination){
        if (vaccination.getExpirationDate().isAfter(LocalDateTime.now())){
            return VaccinationState.VALID;
        }else{
            return VaccinationState.EXPIRED;
        }
    }
}
