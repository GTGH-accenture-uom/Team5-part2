package team5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import team5.services.DoctorService;
import team5.services.InsuredService;
import team5.services.VaccinationCenterService;

@SpringBootApplication
public class App {


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
