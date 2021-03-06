package team5.config;

import org.apache.commons.io.input.TimestampedObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team5.exceptions.DoctorNotFoundException;
import team5.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import team5.services.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SyntheticData implements CommandLineRunner {

    private final InsuredService insuredService;
    private final DoctorService doctorService;
    private final TimeslotService timeslotService;
    private final VaccinationCenterService vaccinationCenterService;
    private final VaccinationService vaccinationService;
    private final ReservationService reservationService;

    private final Logger logger = LoggerFactory.getLogger(SyntheticData.class);

    @Autowired
    public SyntheticData(InsuredService insuredService, DoctorService doctorService, TimeslotService timeslotService,
                         VaccinationCenterService vaccinationCenterService, ReservationService reservationService,
                         VaccinationService vaccinationService) {
        this.insuredService = insuredService;
        this.doctorService = doctorService;
        this.timeslotService = timeslotService;
        this.vaccinationCenterService = vaccinationCenterService;
        this.reservationService = reservationService;
        this.vaccinationService = vaccinationService;
        ///test
    }

    @Override
    public void run(String... args) throws Exception {


        insuredService.createInsured("111111127", "22222223333", "makis", LocalDate.of(1950, 8, 23), "papadopoulos", "mail@mail.gr");
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

        //Creation of Vaccination centers
        VaccinationCenter vaccCenter1 = vaccinationCenterService.createVaccinationCenter("5012", "Thessaloniki", "Egnatias 10");
        VaccinationCenter vaccCenter2 = vaccinationCenterService.createVaccinationCenter("4356", "Thessaloniki", "Egnatias 54");

        //Creation of Doctors
        Doctor d1 = doctorService.createDoctor("12345678912", "nikolaos", "vasileiou");
        Doctor d2 = doctorService.createDoctor("12345678919", "vasilis", "dimitriou");
        Doctor d3 = doctorService.createDoctor("12345678914", "maria", "dimitriou");
        Doctor d4 = doctorService.createDoctor("12345678915", "xristina", "deli");

        //Timeslots center1
        Timeslot t1 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 18, 30), 30);
        Timeslot t2 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 9, 30), 30);
        Timeslot t3 = timeslotService.createTimeslot(LocalDateTime.of(2022, 9, 25, 14, 30), 30);
        Timeslot t4 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 19, 30), 30);
        Timeslot t5 = timeslotService.createTimeslot(LocalDateTime.of(2022, 9, 25, 18, 30), 30);
        Timeslot t6 = timeslotService.createTimeslot(LocalDateTime.of(2022, 8, 21, 18, 30), 30);
        Timeslot t7 = timeslotService.createTimeslot(LocalDateTime.of(2022, 8, 20, 7, 30), 30);
        Timeslot t8 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 18, 30), 30);
        Timeslot t9 = timeslotService.createTimeslot(LocalDateTime.of(2022, 8, 20, 15, 30), 30);
        Timeslot t10 = timeslotService.createTimeslot(LocalDateTime.of(2022, 8, 20, 18, 30), 30);
        //Timeslots center2
        Timeslot t11 = timeslotService.createTimeslot(LocalDateTime.of(2022, 8, 21, 14, 30), 30);
        Timeslot t12 = timeslotService.createTimeslot(LocalDateTime.of(2022, 9, 25, 10, 30), 30);
        Timeslot t13 = timeslotService.createTimeslot(LocalDateTime.of(2022, 12, 10, 17, 30), 30);
        Timeslot t14 = timeslotService.createTimeslot(LocalDateTime.of(2022, 12, 10, 15, 30), 30);
        Timeslot t15 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 20, 30), 30);
        Timeslot t16 = timeslotService.createTimeslot(LocalDateTime.of(2022, 11, 7, 13, 30), 30);
        Timeslot t17 = timeslotService.createTimeslot(LocalDateTime.of(2022, 6, 24, 18, 30), 30);
        Timeslot t18 = timeslotService.createTimeslot(LocalDateTime.of(2022, 11, 7, 9, 30), 30);
        Timeslot t19 = timeslotService.createTimeslot(LocalDateTime.of(2022, 12, 10, 12, 30), 30);
        Timeslot t20 = timeslotService.createTimeslot(LocalDateTime.of(2022, 11, 7, 16, 30), 30);

        //FirstCenter Timeslots
        doctorService.addTimeslotToDoctor("12345678912", t1);
        doctorService.addTimeslotToDoctor("12345678912", t2);
        doctorService.addTimeslotToDoctor("12345678912", t3);
        doctorService.addTimeslotToDoctor("12345678912", t4);
        doctorService.addTimeslotToDoctor("12345678912", t5);

        doctorService.addTimeslotToDoctor("12345678919", t6);
        doctorService.addTimeslotToDoctor("12345678919", t7);
        doctorService.addTimeslotToDoctor("12345678919", t8);
        doctorService.addTimeslotToDoctor("12345678919", t9);
        doctorService.addTimeslotToDoctor("12345678919", t10);

        //Second Center Timeslots
        doctorService.addTimeslotToDoctor("12345678914", t11);
        doctorService.addTimeslotToDoctor("12345678914", t12);
        doctorService.addTimeslotToDoctor("12345678914", t13);
        doctorService.addTimeslotToDoctor("12345678914", t14);
        doctorService.addTimeslotToDoctor("12345678914", t15);
        doctorService.addTimeslotToDoctor("12345678915", t16);
        doctorService.addTimeslotToDoctor("12345678915", t17);
        doctorService.addTimeslotToDoctor("12345678915", t18);
        doctorService.addTimeslotToDoctor("12345678915", t19);
        doctorService.addTimeslotToDoctor("12345678915", t20);

        logger.warn("!!!" + doctorService.findDoctorByAmka("12345678912"));


        reservationService.createReservation("22202223333", t6, "12345678919");
        reservationService.createReservation("22202223333", t7, "12345678919");
        reservationService.createReservation("22202223333", t8, "12345678919");
        reservationService.createReservation("22222223309", t9, "12345678919");
        reservationService.createReservation("22222223309", t10, "12345678919");
        reservationService.createReservation("22222223309", t4, "12345678912");
        reservationService.createReservation("22222223309", t2, "12345678912");


        vaccCenter1.addTimeSlot(t1);
        vaccCenter1.addTimeSlot(t2);
        vaccCenter1.addTimeSlot(t3);
        vaccCenter1.addTimeSlot(t4);
        vaccCenter1.addTimeSlot(t5);
        vaccCenter1.addTimeSlot(t5);
        vaccCenter1.addTimeSlot(t6);
        vaccCenter1.addTimeSlot(t7);
        vaccCenter1.addTimeSlot(t8);
        vaccCenter1.addTimeSlot(t9);
        vaccCenter1.addTimeSlot(t10);

        vaccCenter2.addTimeSlot(t11);
        vaccCenter2.addTimeSlot(t12);
        vaccCenter2.addTimeSlot(t13);
        vaccCenter2.addTimeSlot(t14);
        vaccCenter2.addTimeSlot(t15);
        vaccCenter2.addTimeSlot(t16);
        vaccCenter2.addTimeSlot(t17);
        vaccCenter2.addTimeSlot(t18);
        vaccCenter2.addTimeSlot(t19);
        vaccCenter2.addTimeSlot(t20);

        logger.info("----------------VACCINATION CENTER 1 TIMESLOTS----------------");
        vaccCenter1.getTimeslots().forEach(e -> logger.info("" + e));
        logger.info("----------------VACCINATION CENTER 2 TIMESLOTS----------------");
        vaccCenter2.getTimeslots().forEach(e -> logger.info("" + e));
        logger.info("--------------------------------------------------------------");
    }
}
