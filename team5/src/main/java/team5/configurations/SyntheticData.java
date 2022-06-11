package team5.configurations;

import team5.model.Insured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import team5.services.DoctorService;
import team5.services.InsuredService;
import team5.services.TimeslotService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class SyntheticData implements CommandLineRunner {

    private final InsuredService insuredService;
    private final DoctorService doctorService;
    private final TimeslotService timeslotService;

    @Autowired
    public SyntheticData(InsuredService insuredService, DoctorService doctorService, TimeslotService timeslotService){
        this.insuredService = insuredService;
        this.doctorService = doctorService;
        this.timeslotService = timeslotService;
    }

    @Override
    public void run(String... args) throws Exception {

        insuredService.createInsured("111111127", "22222223333", "makis", LocalDate.of(1950, 8, 23),"papadopoulos", "mail@mail.gr");
        insuredService.createInsured("111611137", "22202223333", "takis", LocalDate.of(1930, 6, 20), "papas", "mail2@mail.gr");
        insuredService.createInsured("111111119", "22222223309", "sakis", LocalDate.of(1990, 8, 23), "euaggelou", "mail3@mail.gr");
        insuredService.createInsured("113111170", "22202223330", "lakis", LocalDate.of(2001, 6, 27), "nikou", "mail4@mail.gr");
        insuredService.createInsured("213111177", "32222223333", "giotis", LocalDate.of(2001, 8, 23), "papadopoulos", "mai99l@mail.gr");
        insuredService.createInsured("211811157", "32205223333", "nikos", LocalDate.of(1991, 6, 20), "papafotiou", "mail22@mail.gr");
        insuredService.createInsured("211121179", "32223223309", "alexis", LocalDate.of(1980, 8, 23), "euaggelou", "mail23@mail.gr");
        insuredService.createInsured("211111700", "32202223330", "maria", LocalDate.of(1945, 6, 27), "nikou", "mail24@mail.gr");
        insuredService.createInsured("111121770", "22224223333", "makis", LocalDate.of(1950, 8, 23), "nikolaou", "mail8@mail.gr");
        insuredService.createInsured("333333333", "22202623333", "mairi", LocalDate.of(1999, 6, 20), "papas", "mail28@mail.gr");
        insuredService.createInsured("111211179", "22222223309", "gianna", LocalDate.of(2015, 8, 23), "euaggelou", "mail38@mail.gr");
        insuredService.createInsured("111111170", "22208223330", "lakis", LocalDate.of(2013, 6, 27), "nikou", "mail48@mail.gr");
        insuredService.createInsured("211129177", "32228223333", "giotis", LocalDate.of(2010, 8, 23), "andreou", "mail88@mail.gr");
        insuredService.createInsured("221111177", "32202723333", "xrisa", LocalDate.of(2017, 6, 20), "papas", "mail228@mail.gr");
        insuredService.createInsured("211131179", "32228223309", "alexis", LocalDate.of(1930, 8, 23), "nakis", "mail238@mail.gr");

        doctorService.createDoctor("12345678912", "nikolaos", "vasileiou");
        doctorService.createDoctor("12345678919", "vasilis", "dimitriou");
        doctorService.createDoctor("12345678914", "maria", "dimitriou");
        doctorService.createDoctor("12345678915", "xristina", "deli");




        long t1 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 18, 30), 30);
        long t2 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 9, 30), 30);
        long t3 = timeslotService.createTimeslot(LocalDateTime.of(2022, 9, 25, 14, 30), 30);
        long t4 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 19, 30), 30);
        long t5 = timeslotService.createTimeslot(LocalDateTime.of(2022, 9, 25, 18, 30), 30);

        long t6 = timeslotService.createTimeslot(LocalDateTime.of(2022, 9, 25, 18, 30), 30);
        long t7 = timeslotService.createTimeslot(LocalDateTime.of(2022, 8, 20, 7, 30), 30);
        long t8 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 18, 30), 30);
        long t9 = timeslotService.createTimeslot(LocalDateTime.of(2022, 8, 20, 15, 30), 30);
        long t10 = timeslotService.createTimeslot(LocalDateTime.of(2022, 8, 20, 18, 30), 30);

        long t11 = timeslotService.createTimeslot(LocalDateTime.of(2022, 8, 20, 14, 30), 30);
        long t12 = timeslotService.createTimeslot(LocalDateTime.of(2022, 9, 25, 10, 30), 30);
        long t13 = timeslotService.createTimeslot(LocalDateTime.of(2022, 12, 10, 17, 30), 30);
        long t14 = timeslotService.createTimeslot(LocalDateTime.of(2022, 12, 10, 15, 30), 30);
        long t15 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 20, 30), 30);

        long t16 = timeslotService.createTimeslot(LocalDateTime.of(2022, 11, 7, 13, 30), 30);
        long t17 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 18, 30), 30);
        long t18 = timeslotService.createTimeslot(LocalDateTime.of(2022, 11, 7, 9, 30), 30);
        long t19 = timeslotService.createTimeslot(LocalDateTime.of(2022, 12, 10, 12, 30), 30);
        long t20 = timeslotService.createTimeslot(LocalDateTime.of(2022, 11, 7, 16, 30), 30);

        //doctorService.addTimeslotToDoctor();



//        //Reservations for Center1
//        vaccinationCenterService.createReservation(insured1, timeslot1center1, vaccCenter1);
//        vaccinationCenterService.createReservation(insured2, timeslot2center1, vaccCenter1);
//        vaccinationCenterService.createReservation(insured3, timeslot3center1, vaccCenter1);
//        vaccinationCenterService.createReservation(insured4, timeslot4center1, vaccCenter1);
//
//        //Reservations for Center2
//        vaccinationCenterService.createReservation(insured5, timeslot1center2, vaccCenter2);
//        vaccinationCenterService.createReservation(insured6, timeslot2center2, vaccCenter2);
//        vaccinationCenterService.createReservation(insured7, timeslot3center2, vaccCenter2);
//        vaccinationCenterService.createReservation(insured8, timeslot4center2, vaccCenter2);
//
//        //vaccinations
//        vaccinationCenterService.createVaccination("Pfizer", 2, insured1, vaccCenter1);
//        vaccinationCenterService.createVaccination("Moderna", 3, insured2, vaccCenter1);
//        vaccinationCenterService.createVaccination("Pfizer", 2, insured3, vaccCenter1);
//        vaccinationCenterService.createVaccination("Pfizer", 2, insured4, vaccCenter1);
//        vaccinationCenterService.createVaccination("Pfizer", 2, insured5, vaccCenter2);
//        vaccinationCenterService.createVaccination("Pfizer", 2, insured6, vaccCenter2);


    }
}