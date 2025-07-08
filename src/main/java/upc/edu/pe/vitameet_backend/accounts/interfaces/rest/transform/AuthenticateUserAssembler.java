package upc.edu.pe.vitameet_backend.accounts.interfaces.rest.transform;

import upc.edu.pe.vitameet_backend.accounts.domain.model.queries.AuthenticateUserQuery;
import upc.edu.pe.vitameet_backend.accounts.interfaces.rest.resource.AuthenticateUserResource;

public class AuthenticateUserAssembler {
    public static AuthenticateUserQuery toQueryFromResource(AuthenticateUserResource resource) {
        return new AuthenticateUserQuery(resource.email(), resource.password());
    }
}
