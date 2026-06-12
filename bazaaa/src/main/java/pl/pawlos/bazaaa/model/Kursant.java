package pl.pawlos.bazaaa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Kursant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Imię jest wymagane")
    private String imie;

    @NotBlank(message = "Nazwisko jest wymagane")
    private String nazwisko;

    @Pattern(regexp = "\\d{11}", message = "PESEL musi mieć 11 cyfr")
    private String pesel;

    @Pattern(regexp = "\\d{9}", message = "Telefon musi mieć 9 cyfr")
    private String telefon;

    @Email(message = "Niepoprawny email")
    private String email;

    @ManyToOne
    private Kurs kurs;

    // gettery i settery
}
