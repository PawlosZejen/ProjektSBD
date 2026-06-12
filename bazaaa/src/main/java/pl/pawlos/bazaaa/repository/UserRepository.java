package pl.pawlos.bazaaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawlos.bazaaa.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
