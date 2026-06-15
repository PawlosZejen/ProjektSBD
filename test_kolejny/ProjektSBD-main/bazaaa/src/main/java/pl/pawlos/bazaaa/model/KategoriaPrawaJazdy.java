package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kategoriaprawajazdy")
public class KategoriaPrawaJazdy {

    @Id
    @Column(name = "id_kategorii", length = 10)
    private String idKategorii;

    @Column(name = "wymagany_wiek", nullable = false)
    private Integer wymaganyWiek;

    @Column(name = "opis", length = 100)
    private String opis;

    // Gettery i settery
    public String getIdKategorii() { return idKategorii; }
    public void setIdKategorii(String idKategorii) { this.idKategorii = idKategorii; }

    public Integer getWymaganyWiek() { return wymaganyWiek; }
    public void setWymaganyWiek(Integer wymaganyWiek) { this.wymaganyWiek = wymaganyWiek; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
}
