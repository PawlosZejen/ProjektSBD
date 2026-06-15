package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Jazda")
public class Jazda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jazdy")
    private Long idJazdy;

    @ManyToOne
    @JoinColumn(name = "id_kursu", nullable = false)
    private Kurs kurs;

    @ManyToOne
    @JoinColumn(name = "id_instruktora", nullable = false)
    private Instruktor instruktor;

    @ManyToOne
    @JoinColumn(name = "nr_rejestracyjny", nullable = false)
    private Pojazd pojazd;

    @Column(name = "termin", nullable = false)
    private java.sql.Timestamp termin;

    @Column(name = "czas_trwania_godz")
    private Integer czasTrwaniaGodz;

    @Column(name = "status", length = 20)
    private String status;

    // Gettery i settery
}
