package upc.edu.pe.vitameet_backend.accounts.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.edu.pe.vitameet_backend.accounts.domain.model.aggregates.User;
import upc.edu.pe.vitameet_backend.accounts.domain.model.commands.RegisterUserCommand;
import upc.edu.pe.vitameet_backend.accounts.domain.model.valueobjects.Email;
import upc.edu.pe.vitameet_backend.accounts.domain.model.valueobjects.Password;
import upc.edu.pe.vitameet_backend.accounts.infrastructure.persistence.jpa.repositories.UserRepository;

@Service
public class RegisterUserService {

    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User handle(RegisterUserCommand command) {
        var email = new Email(command.email());
        var password = new Password(command.password());
        var user = new User(email, password);
        return userRepository.save(user);
    }
}
