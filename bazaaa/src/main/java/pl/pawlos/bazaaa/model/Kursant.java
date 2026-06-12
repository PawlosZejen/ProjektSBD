package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Kursant")
public class Kursant {

    @Id
    @Column(name = "pesel", length = 11)
    private String pesel;

    @Column(name = "imie", length = 50, nullable = false)
    private String imie;

    @Column(name = "nazwisko", length = 50, nullable = false)
    private String nazwisko;

    @Column(name = "data_urodzenia", nullable = false)
    private java.sql.Date dataUrodzenia;

    @Column(name = "telefon", length = 15)
    private String telefon;

    @Column(name = "email", length = 100)
    private String email;

    // Gettery i settery
}
