package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Platnosc")
public class Platnosc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_platnosci")
    private Long idPlatnosci;

    @ManyToOne
    @JoinColumn(name = "id_kursu", nullable = false)
    private Kurs kurs;

    @Column(name = "kwota", nullable = false)
    private Double kwota;

    @Column(name = "data_wplaty")
    private java.sql.Timestamp dataWplaty;

    @Column(name = "metoda_platnosci", length = 30)
    private String metodaPlatnosci;

    // Gettery i settery
}
