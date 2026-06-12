package pl.pawlos.bazaaa;

import pl.pawlos.bazaaa.service.UczenService;

public class Main {
    public static void main(String[] args) {
        UczenService service = new UczenService();

        service.getAll().forEach(u ->
                System.out.println(u.getPesel() + " " + u.getImie() + " " + u.getNazwisko())
        );
    }
}
