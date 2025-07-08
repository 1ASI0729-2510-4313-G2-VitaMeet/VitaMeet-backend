package upc.edu.pe.vitameet_backend.accounts.infrastructure.services;

import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet_backend.accounts.domain.model.aggregates.User;
import upc.edu.pe.vitameet_backend.accounts.domain.services.AuthenticationService;
import upc.edu.pe.vitameet_backend.accounts.infrastructure.persistence.jpa.repositories.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return userRepository.findByEmailAddress(email)
                .map(user -> user.getPassword().getValue().equals(password))
                .orElse(false);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmailAddress(email).orElse(null);
    }
}
