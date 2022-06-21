package team5.dto;

import java.time.LocalDateTime;

public class VaccinationDTO {

    private long timeslotId;
    private String insuredAmka;
    private String vacc_Name;
    private LocalDateTime expirationDate;

    public long getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(long timeslotId) {
        this.timeslotId = timeslotId;
    }

    public String getInsuredAmka() {
        return insuredAmka;
    }

    public void setInsuredAmka(String insuredAmka) {
        this.insuredAmka = insuredAmka;
    }

    public String getVacc_Name() {
        return vacc_Name;
    }

    public void setVacc_Name(String vacc_Name) {
        this.vacc_Name = vacc_Name;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
