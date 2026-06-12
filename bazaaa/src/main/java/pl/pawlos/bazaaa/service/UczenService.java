package pl.pawlos.bazaaa.service;

import pl.pawlos.bazaaa.dao.UczenDAO;
import pl.pawlos.bazaaa.model.Uczen;

import java.util.List;

public class UczenService {

    private final UczenDAO dao = new UczenDAO();

    public List<Uczen> getAll() {
        return dao.findAll();
    }
}
