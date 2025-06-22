package upc.edu.pe.vitameet.profile.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.vitameet.profile.application.internal.commandservices.ProfileCommandServiceImpl;
import upc.edu.pe.vitameet.profile.domain.model.commands.CreateProfileCommand;
import upc.edu.pe.vitameet.profile.interfaces.rest.resource.ProfileResource;
import upc.edu.pe.vitameet.profile.interfaces.rest.transform.ProfileResourceFromEntityAssembler;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@Tag(name = "Profiles", description = "API for managing user profiles")
public class ProfileController {

    private final ProfileCommandServiceImpl service;

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileCommand command) {
        var profile = service.handle(command);
        var resource = ProfileResourceFromEntityAssembler.toResource(profile);
        return ResponseEntity.ok(resource);
    }
}
