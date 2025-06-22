package upc.edu.pe.vitameet.authentication.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet.authentication.domain.model.aggregates.AuthUser;
import upc.edu.pe.vitameet.authentication.domain.model.commands.RegisterUserCommand;
import upc.edu.pe.vitameet.authentication.domain.services.AuthCommandService;
import upc.edu.pe.vitameet.authentication.infrastructure.persistence.jpa.repositories.AuthUserRepository;

@Service
public class AuthCommandServiceImpl implements AuthCommandService {

    private final AuthUserRepository repository;

    public AuthCommandServiceImpl(AuthUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthUser handle(RegisterUserCommand command) {
        return repository.save(new AuthUser(command));
    }
}
