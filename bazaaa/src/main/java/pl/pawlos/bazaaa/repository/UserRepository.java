package pl.pawlos.bazaaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawlos.bazaaa.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
