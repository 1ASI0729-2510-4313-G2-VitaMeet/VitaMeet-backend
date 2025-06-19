package upc.edu.pe.vitameet_backend.accounts.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet_backend.accounts.domain.exceptions.UserNotFoundException;
import upc.edu.pe.vitameet_backend.accounts.domain.model.aggregates.User;
import upc.edu.pe.vitameet_backend.accounts.domain.model.queries.AuthenticateUserQuery;
import upc.edu.pe.vitameet_backend.accounts.infrastructure.persistence.jpa.repositories.UserRepository;

@Service
public class AuthenticateUserService {

    private final UserRepository userRepository;

    public AuthenticateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handle(AuthenticateUserQuery query) {
        return userRepository
                .findByEmailAddress(query.email())
                .filter(user -> user.getPassword().getValue().equals(query.password()))
                .orElseThrow(() -> new UserNotFoundException("Invalid credentials"));
    }
}
