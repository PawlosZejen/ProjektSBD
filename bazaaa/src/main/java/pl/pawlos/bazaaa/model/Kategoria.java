package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kategorie")
public class Kategoria {

    @Id
    @Column(name = "id_kategorii")
    private String id;

    @Column(name = "wymagany_wiek")
    private int wymaganyWiek;

    private String opis;

    public Kategoria() {}

    public Kategoria(String id, int wymaganyWiek, String opis) {
        this.id = id;
        this.wymaganyWiek = wymaganyWiek;
        this.opis = opis;
    }

    // gettery i settery
}
