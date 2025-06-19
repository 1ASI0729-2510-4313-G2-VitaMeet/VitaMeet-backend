package upc.edu.pe.vitameet_backend.accounts.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.vitameet_backend.accounts.application.internal.commandservices.RegisterUserService;
import upc.edu.pe.vitameet_backend.accounts.application.internal.queryservices.AuthenticateUserService;
import upc.edu.pe.vitameet_backend.accounts.domain.model.aggregates.User;
import upc.edu.pe.vitameet_backend.accounts.interfaces.rest.resource.*;
import upc.edu.pe.vitameet_backend.accounts.interfaces.rest.transform.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AuthController {

    private final RegisterUserService registerUserService;
    private final AuthenticateUserService authenticateUserService;

    public AuthController(RegisterUserService registerUserService, AuthenticateUserService authenticateUserService) {
        this.registerUserService = registerUserService;
        this.authenticateUserService = authenticateUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserResource resource) {
        var command = RegisterUserAssembler.toCommandFromResource(resource);
        var user = registerUserService.handle(command);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthenticateUserResource resource) {
        var query = AuthenticateUserAssembler.toQueryFromResource(resource);
        var user = authenticateUserService.handle(query);
        return ResponseEntity.ok(user);
    }
}
