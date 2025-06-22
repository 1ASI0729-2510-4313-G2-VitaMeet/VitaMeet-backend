package upc.edu.pe.vitameet.profile.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet.profile.domain.model.aggregates.Profile;
import upc.edu.pe.vitameet.profile.domain.model.commands.CreateProfileCommand;
import upc.edu.pe.vitameet.profile.domain.services.ProfileCommandService;
import upc.edu.pe.vitameet.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository repository;

    public ProfileCommandServiceImpl(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Profile handle(CreateProfileCommand command) {
        Profile profile = new Profile(command);
        return repository.save(profile);
    }
}
