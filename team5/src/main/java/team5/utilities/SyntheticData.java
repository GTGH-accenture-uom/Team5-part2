package team5.utilities;

import team5.model.Insured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import team5.services.InsuredService;
import java.time.LocalDate;

@Configuration
public class SyntheticData implements CommandLineRunner {

    private final InsuredService insuredService;

    @Autowired
    public SyntheticData(InsuredService insuredService){
        this.insuredService = insuredService;
    }

    @Override
    public void run(String... args) throws Exception {

        Insured insured1 = new Insured("111111127", "22222223333", "makis", LocalDate.of(1950, 8, 23), "papadopoulos", "mail@mail.gr");
        Insured insured2 = new Insured("111611137", "22202223333", "takis", LocalDate.of(1930, 6, 20), "papas", "mail2@mail.gr");
        Insured insured3 = new Insured("111111119", "22222223309", "sakis", LocalDate.of(1990, 8, 23), "euaggelou", "mail3@mail.gr");
        Insured insured4 = new Insured("113111170", "22202223330", "lakis", LocalDate.of(2001, 6, 27), "nikou", "mail4@mail.gr");
        Insured insured5 = new Insured("213111177", "32222223333", "giotis", LocalDate.of(2001, 8, 23), "papadopoulos", "mai99l@mail.gr");
        Insured insured6 = new Insured("211811157", "32205223333", "nikos", LocalDate.of(1991, 6, 20), "papafotiou", "mail22@mail.gr");
        Insured insured7 = new Insured("211121179", "32223223309", "alexis", LocalDate.of(1980, 8, 23), "euaggelou", "mail23@mail.gr");
        Insured insured8 = new Insured("211111700", "32202223330", "maria", LocalDate.of(1945, 6, 27), "nikou", "mail24@mail.gr");
        Insured insured9 = new Insured("111121770", "22224223333", "makis", LocalDate.of(1950, 8, 23), "nikolaou", "mail8@mail.gr");
        Insured insured10 = new Insured("333333333", "22202623333", "mairi", LocalDate.of(1999, 6, 20), "papas", "mail28@mail.gr");
        Insured insured11 = new Insured("111211179", "22222223309", "gianna", LocalDate.of(2015, 8, 23), "euaggelou", "mail38@mail.gr");
        Insured insured12 = new Insured("111111170", "22208223330", "lakis", LocalDate.of(2013, 6, 27), "nikou", "mail48@mail.gr");
        Insured insured13 = new Insured("211129177", "32228223333", "giotis", LocalDate.of(2010, 8, 23), "andreou", "mail88@mail.gr");
        Insured insured14 = new Insured("221111177", "32202723333", "xrisa", LocalDate.of(2017, 6, 20), "papas", "mail228@mail.gr");
        Insured insured15 = new Insured("211131179", "32228223309", "alexis", LocalDate.of(1930, 8, 23), "nakis", "mail238@mail.gr");



        insuredService.createInsured("111111127", "22222223333", "makis", LocalDate.of(1950, 8, 23),"papadopoulos", "mail@mail.gr");


    }
}
