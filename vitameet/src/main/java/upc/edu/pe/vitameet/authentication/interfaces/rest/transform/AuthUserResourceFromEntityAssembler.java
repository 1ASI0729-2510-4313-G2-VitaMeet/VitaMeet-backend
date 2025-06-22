package upc.edu.pe.vitameet.authentication.interfaces.rest.transform;

import upc.edu.pe.vitameet.authentication.domain.model.aggregates.AuthUser;
import upc.edu.pe.vitameet.authentication.interfaces.rest.resource.AuthUserResource;

public class AuthUserResourceFromEntityAssembler {
    public static AuthUserResource toResource(AuthUser entity) {
        return new AuthUserResource(
                entity.getId(),
                entity.getUsername(),
                entity.getRole().name()
        );
    }
}
