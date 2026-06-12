package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Obecnosc_wyklady")
public class ObecnoscWyklady {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obecnosci")
    private Long idObecnosci;

    @ManyToOne
    @JoinColumn(name = "id_wykladu")
    private Wyklady wyklad;

    @ManyToOne
    @JoinColumn(name = "pesel_kursanta")
    private Kursant kursant;

    // Gettery i settery
}
