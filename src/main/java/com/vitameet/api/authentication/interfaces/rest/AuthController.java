package com.vitameet.api.authentication.interfaces.rest;

import com.vitameet.api.authentication.application.commands.LoginCommand;
import com.vitameet.api.authentication.application.commands.RegisterCommand;
import com.vitameet.api.authentication.application.services.AuthenticationService;
import com.vitameet.api.authentication.interfaces.rest.dto.AuthResponse;
import com.vitameet.api.authentication.interfaces.rest.dto.LoginRequest;
import com.vitameet.api.authentication.interfaces.rest.dto.RegisterRequest;
import com.vitameet.api.medicalstaff.domain.model.Doctor;
import com.vitameet.api.usermanagement.domain.model.Patient;
import com.vitameet.api.usermanagement.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            RegisterCommand command = new RegisterCommand(
                    request.name(),
                    request.email(),
                    request.password(),
                    request.role(),
                    request.specialty(),
                    request.license(),
                    request.experience(),
                    request.age(),
                    request.phone(),
                    request.address(),
                    request.diagnosis(),
                    request.treatment(),
                    request.date());

            User user = authenticationService.register(command);
            AuthResponse response = mapToAuthResponse(user, request);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        LoginCommand command = new LoginCommand(request.email(), request.password());
        Optional<Object> userOpt = authenticationService.login(command);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Object user = userOpt.get();
        AuthResponse response = mapEntityToAuthResponse(user);

        return ResponseEntity.ok(response);
    }

    private AuthResponse mapToAuthResponse(User user, RegisterRequest request) {
        return new AuthResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                request.specialty(),
                request.license(),
                request.experience(),
                request.age(),
                request.phone(),
                request.address(),
                request.diagnosis(),
                request.treatment(),
                request.date());
    }

    private AuthResponse mapEntityToAuthResponse(Object entity) {
        if (entity instanceof Doctor doctor) {
            return new AuthResponse(
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getEmail(),
                    doctor.getRole(),
                    doctor.getSpecialty(),
                    doctor.getLicense(),
                    doctor.getExperience(),
                    null, null, null, null, null, null);
        } else if (entity instanceof Patient patient) {
            return new AuthResponse(
                    patient.getId(),
                    patient.getName(),
                    patient.getEmail(),
                    patient.getRole(),
                    null, null, null,
                    patient.getAge(),
                    patient.getPhone(),
                    patient.getAddress(),
                    patient.getDiagnosis(),
                    patient.getTreatment(),
                    patient.getDate());
        } else if (entity instanceof User user) {
            return new AuthResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole(),
                    null, null, null, null, null, null, null, null, null);
        }
        return null;
    }
}
