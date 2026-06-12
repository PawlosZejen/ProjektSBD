package pl.pawlos.bazaaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        UczenService service = new UczenService();

        service.getAll().forEach(u ->
                System.out.println(u.getPesel() + " " + u.getImie() + " " + u.getNazwisko())
        );
        SpringApplication.run(Main.class, args);
    }
}
