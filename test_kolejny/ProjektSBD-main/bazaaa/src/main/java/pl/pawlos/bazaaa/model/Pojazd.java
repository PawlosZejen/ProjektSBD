package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Pojazd")
public class Pojazd {

    @Id
    @Column(name = "nr_rejestracyjny", length = 15)
    private String nrRejestracyjny;

    @ManyToOne
    @JoinColumn(name = "id_kategorii", nullable = false)
    private KategoriaPrawaJazdy kategoria;

    @Column(name = "marka", length = 50, nullable = false)
    private String marka;

    @Column(name = "model", length = 50, nullable = false)
    private String model;

    @Column(name = "przebieg")
    private Integer przebieg;

    // Gettery i settery
}
