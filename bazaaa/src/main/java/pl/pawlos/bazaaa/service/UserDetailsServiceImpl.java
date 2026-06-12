package pl.pawlos.bazaaa.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pawlos.bazaaa.model.User;
import pl.pawlos.bazaaa.repository.UserRepository;
import pl.pawlos.bazaaa.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repo;

    public UserDetailsServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repo.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika: " + login);
        }

        return new UserDetailsImpl(user);
    }
}
