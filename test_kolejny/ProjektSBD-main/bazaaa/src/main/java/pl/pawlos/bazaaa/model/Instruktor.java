package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Instruktor")
public class Instruktor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instruktora")
    private Long idInstruktora;

    @Column(name = "imie", length = 50, nullable = false)
    private String imie;

    @Column(name = "nazwisko", length = 50, nullable = false)
    private String nazwisko;

    @Column(name = "telefon", length = 15, nullable = false)
    private String telefon;

    @ManyToOne
    @JoinColumn(name = "id_kategorii_glownej")
    private KategoriaPrawaJazdy kategoriaGlowna;

    @ManyToOne
    @JoinColumn(name = "domyslny_pojazd")
    private Pojazd domyslnyPojazd;

    // Gettery i settery
}
