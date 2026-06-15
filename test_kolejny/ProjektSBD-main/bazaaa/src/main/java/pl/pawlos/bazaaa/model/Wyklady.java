package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Wyklady")
public class Wyklady {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wykladu")
    private Long idWykladu;

    @ManyToOne
    @JoinColumn(name = "id_instruktora", nullable = false)
    private Instruktor instruktor;

    @ManyToOne
    @JoinColumn(name = "id_kategorii", nullable = false)
    private KategoriaPrawaJazdy kategoria;

    @Column(name = "termin", nullable = false)
    private java.sql.Timestamp termin;

    @Column(name = "numer_sali")
    private Integer numerSali;

    @Column(name = "czas_trwania_godz")
    private Integer czasTrwaniaGodz;

    // Gettery i settery
}
