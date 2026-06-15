package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Kurs")
public class Kurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kursu")
    private Long idKursu;

    @ManyToOne
    @JoinColumn(name = "pesel_kursanta", nullable = false)
    private Kursant kursant;

    @ManyToOne
    @JoinColumn(name = "id_kategorii", nullable = false)
    private KategoriaPrawaJazdy kategoria;

    @ManyToOne
    @JoinColumn(name = "id_instruktora")
    private Instruktor instruktor;

    @Column(name = "data_rozpoczecia")
    private java.sql.Date dataRozpoczecia;

    @Column(name = "status", length = 20)
    private String status;

    // Gettery i settery
}
