package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "kursant")
public class Kursant {

    @Id
    @Column(name = "pesel", length = 11)
    private String pesel;

    @Column(name = "imie", length = 50, nullable = false)
    private String imie;

    @Column(name = "nazwisko", length = 50, nullable = false)
    private String nazwisko;

    @Column(name = "data_urodzenia", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_urodzenia;

    @Column(name = "telefon", length = 15)
    private String telefon;

    @Column(name = "email", length = 100)
    private String email;

    // === GETTERY ===
    public String getPesel() { return pesel; }
    public String getImie() { return imie; }
    public String getNazwisko() { return nazwisko; }
    public LocalDate getData_urodzenia() { return data_urodzenia; }
    public String getTelefon() { return telefon; }
    public String getEmail() { return email; }

    // === SETTERY ===
    public void setPesel(String pesel) { this.pesel = pesel; }
    public void setImie(String imie) { this.imie = imie; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }
    public void setData_urodzenia(LocalDate data_urodzenia) { this.data_urodzenia = data_urodzenia; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public void setEmail(String email) { this.email = email; }
}
