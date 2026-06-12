package pl.pawlos.bazaaa.service;

import org.springframework.stereotype.Service;
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
}
