package upc.edu.pe.vitameet.profile.interfaces.rest.transform;

import upc.edu.pe.vitameet.profile.domain.model.aggregates.Profile;
import upc.edu.pe.vitameet.profile.interfaces.rest.resource.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResource(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getRole()
        );
    }
}
