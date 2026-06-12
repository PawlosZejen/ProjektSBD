package pl.pawlos.bazaaa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Uzytkownik") // nazwa tabeli w bazie
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String haslo;

    private String email;

    @ManyToOne
    @JoinColumn(name = "id_roli")
    private Role role;

    // === GETTERY ===
    public Long getId() { return id; }
    public String getLogin() { return login; }
    public String getHaslo() { return haslo; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }

    // === SETTERY ===
    public void setId(Long id) { this.id = id; }
    public void setLogin(String login) { this.login = login; }
    public void setHaslo(String haslo) { this.haslo = haslo; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(Role role) { this.role = role; }
}
