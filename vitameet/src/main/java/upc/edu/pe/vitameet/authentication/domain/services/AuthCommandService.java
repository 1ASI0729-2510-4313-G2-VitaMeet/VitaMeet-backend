package upc.edu.pe.vitameet.authentication.domain.services;

import upc.edu.pe.vitameet.authentication.domain.model.aggregates.AuthUser;
import upc.edu.pe.vitameet.authentication.domain.model.commands.RegisterUserCommand;

public interface AuthCommandService {
    AuthUser handle(RegisterUserCommand command);
}
