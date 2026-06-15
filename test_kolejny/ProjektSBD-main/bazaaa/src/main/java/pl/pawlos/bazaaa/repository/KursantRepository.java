package pl.pawlos.bazaaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawlos.bazaaa.model.Kursant;
import java.util.Optional;

public interface KursantRepository extends JpaRepository<Kursant, String> {
    Optional<Kursant> findByEmail(String email);
}
