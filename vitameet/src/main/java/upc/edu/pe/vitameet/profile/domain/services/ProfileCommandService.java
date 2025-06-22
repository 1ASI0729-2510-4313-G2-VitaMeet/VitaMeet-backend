package upc.edu.pe.vitameet.profile.domain.services;

import upc.edu.pe.vitameet.profile.domain.model.aggregates.Profile;
import upc.edu.pe.vitameet.profile.domain.model.commands.CreateProfileCommand;

public interface ProfileCommandService {
    Profile handle(CreateProfileCommand command);
}
