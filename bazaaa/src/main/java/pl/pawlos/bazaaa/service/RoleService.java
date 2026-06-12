package pl.pawlos.bazaaa.service;

import org.springframework.stereotype.Service;
import pl.pawlos.bazaaa.model.Role;
import pl.pawlos.bazaaa.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository repo;

    public RoleService(RoleRepository repo) {
        this.repo = repo;
    }

    public List<Role> getAll() {
        return repo.findAll();
    }

    public void save(Role role) {
        repo.save(role);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
