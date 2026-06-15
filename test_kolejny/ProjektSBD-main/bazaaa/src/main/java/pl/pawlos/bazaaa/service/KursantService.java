package pl.pawlos.bazaaa.service;

import org.springframework.stereotype.Service;
import pl.pawlos.bazaaa.model.Kursant;
import pl.pawlos.bazaaa.model.User;
import pl.pawlos.bazaaa.repository.KursantRepository;
import pl.pawlos.bazaaa.repository.UserRepository;
import java.util.List;

@Service
public class KursantService {

    private final KursantRepository repo;

    public KursantService(KursantRepository repo) {
        this.repo = repo;
    }

    public List<Kursant> getAll() {
        return repo.findAll();
    }

    public void save(Kursant kursant) { repo.save(kursant); }

    public void delete(String pesel) {
        repo.deleteById(pesel);
    }

    public Kursant getByPesel(String pesel) {
        return repo.findById(pesel).orElse(null);
    }

    public Kursant getByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    public void update(Kursant updated) {
        Kursant existing = repo.findById(updated.getPesel()).orElseThrow();

        if (updated.getImie() != null && !updated.getImie().isEmpty())
            existing.setImie(updated.getImie());

        if (updated.getNazwisko() != null && !updated.getNazwisko().isEmpty())
            existing.setNazwisko(updated.getNazwisko());

        if (updated.getData_urodzenia() != null)
            existing.setData_urodzenia(updated.getData_urodzenia());

        if (updated.getTelefon() != null && !updated.getTelefon().isEmpty())
            existing.setTelefon(updated.getTelefon());

        if (updated.getEmail() != null && !updated.getEmail().isEmpty())
            existing.setEmail(updated.getEmail());

        repo.save(existing);
    }
}