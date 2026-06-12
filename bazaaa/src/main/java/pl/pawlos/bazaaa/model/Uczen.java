package pl.pawlos.bazaaa.model;

public class Uczen {
    private long pesel;
    private String imie;
    private String nazwisko;

    public Uczen(long pesel, String imie, String nazwisko) {
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public long getPesel() { return pesel; }
    public String getImie() { return imie; }
    public String getNazwisko() { return nazwisko; }
}
