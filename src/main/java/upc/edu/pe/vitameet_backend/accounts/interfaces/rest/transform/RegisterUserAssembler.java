package upc.edu.pe.vitameet_backend.accounts.interfaces.rest.transform;

import upc.edu.pe.vitameet_backend.accounts.domain.model.commands.RegisterUserCommand;
import upc.edu.pe.vitameet_backend.accounts.interfaces.rest.resource.RegisterUserResource;

public class RegisterUserAssembler {
    public static RegisterUserCommand toCommandFromResource(RegisterUserResource resource) {
        return new RegisterUserCommand(resource.email(), resource.password());
    }
}
