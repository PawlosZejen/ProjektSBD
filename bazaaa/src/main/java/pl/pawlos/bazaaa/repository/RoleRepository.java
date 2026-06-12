package pl.pawlos.bazaaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawlos.bazaaa.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNazwa(String nazwa);
}
