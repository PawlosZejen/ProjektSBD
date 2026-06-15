package pl.pawlos.bazaaa.service;

import org.springframework.stereotype.Service;
import pl.pawlos.bazaaa.model.Kursant;
import pl.pawlos.bazaaa.model.User;
import pl.pawlos.bazaaa.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public User getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void update(User updated) {
        User existing = repo.findById(updated.getId()).orElseThrow();

        // only update fields that are not null/empty
        if (updated.getLogin() != null && !updated.getLogin().isEmpty())
            existing.setLogin(updated.getLogin());

        if (updated.getHaslo() != null && !updated.getHaslo().isEmpty())
            existing.setHaslo(updated.getHaslo());

        if (updated.getEmail() != null && !updated.getEmail().isEmpty())
            existing.setEmail(updated.getEmail());

        if (updated.getRole() != null)
            existing.setRole(updated.getRole());

        repo.save(existing);
    }
}
