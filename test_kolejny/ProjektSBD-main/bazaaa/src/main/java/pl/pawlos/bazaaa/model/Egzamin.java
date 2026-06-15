package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Egzamin")
public class Egzamin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_egzaminu")
    private Long idEgzaminu;

    @ManyToOne
    @JoinColumn(name = "id_kursu", nullable = false)
    private Kurs kurs;

    @Column(name = "rodzaj", length = 30, nullable = false)
    private String rodzaj;

    @Column(name = "termin", nullable = false)
    private java.sql.Timestamp termin;

    @Column(name = "wynik", length = 20)
    private String wynik;

    @Column(name = "uwagi")
    private String uwagi;

    // Gettery i settery
}
