package team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.model.Doctor;
import team5.model.Vaccination;
import team5.utilities.VaccinationState;

import java.time.LocalDateTime;
import java.util.*;
@Service
public class VaccinationService {

    private final List<Vaccination> allVaccinations = new ArrayList<>();

    private InsuredService insuredService;
    //private DoctorService doctorService;

    @Autowired
    public VaccinationService(InsuredService insuredService, DoctorService doctorService) {
        this.insuredService = insuredService;
        //this.doctorService = doctorService;
    }

    public List<Vaccination> getAllVaccinations() {
        return allVaccinations;
    }

    public InsuredService getInsuredService() {
        return insuredService;
    }

    public void setInsuredService(InsuredService insuredService) {
        this.insuredService = insuredService;
    }

    public List<Vaccination> getRecentVaccinationsByInsured(String amka){
        List<Vaccination> vaccinationsByInsured = new ArrayList<>();
        Set<String> names = new HashSet<>();
        for (Vaccination v: allVaccinations){
            if (v!=null && v.getInsured()!=null){
                if (v.getInsured().getAmka().equals(amka)){
                    vaccinationsByInsured.add(v);
                    names.add(v.getVacc_brand());
                }
            }else {
                throw new RuntimeException("Cannot get vaccinations by insured.");
            }
        }
        System.out.println("4*********************vaccinationsByInsured");
        System.out.println(vaccinationsByInsured);
        System.out.println("3*************************names");
        System.out.println(names);
        List<Vaccination> RecentVaccinationsByName = new ArrayList<>();
        for (String name: names){
            Vaccination vaccination = getVaccinationByInsuredByVaccine(name, amka, vaccinationsByInsured);
            RecentVaccinationsByName.add(vaccination);
        }
        System.out.println("1.*************************RecentVaccinationsByName");
        System.out.println(RecentVaccinationsByName);
        return RecentVaccinationsByName;
    }


    public  Vaccination getVaccinationByInsuredByVaccine(String vaccine, String amka, List<Vaccination> vaccinationsByInsured){
        try {
            LocalDateTime expirationDateTime = vaccinationsByInsured.get(0).getExpirationDate();
            Vaccination vaccination = vaccinationsByInsured.get(0);
            for (Vaccination v : vaccinationsByInsured) {
                if (v.getVacc_brand().equals(vaccine) && v.getExpirationDate().isAfter(expirationDateTime )) {
                    expirationDateTime = v.getExpirationDate();
                    vaccination = v;
                }
            }
            System.out.println("2.********************vaccination");
            System.out.println(vaccination);
            return vaccination;
        }catch (Exception e){
            throw new RuntimeException("Cannot parse vaccination list by insured bu id");
        }
    }
}
