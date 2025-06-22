package upc.edu.pe.vitameet.authentication.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.vitameet.authentication.application.internal.commandservices.AuthCommandServiceImpl;
import upc.edu.pe.vitameet.authentication.domain.model.commands.RegisterUserCommand;
import upc.edu.pe.vitameet.authentication.interfaces.rest.resource.AuthUserResource;
import upc.edu.pe.vitameet.authentication.interfaces.rest.transform.AuthUserResourceFromEntityAssembler;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "User registration")
public class AuthController {

    private final AuthCommandServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<AuthUserResource> register(@RequestBody RegisterUserCommand command) {
        var user = authService.handle(command);
        return ResponseEntity.ok(AuthUserResourceFromEntityAssembler.toResource(user));
    }
}
