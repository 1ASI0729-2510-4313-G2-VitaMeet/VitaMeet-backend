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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            logger.info("Received register request: {}", request);
            
            // Validación básica
            if (request.name() == null || request.name().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El nombre es requerido");
                return ResponseEntity.badRequest().body(error);
            }
            
            if (request.email() == null || request.email().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El email es requerido");
                return ResponseEntity.badRequest().body(error);
            }
            
            if (request.password() == null || request.password().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "La contraseña es requerida");
                return ResponseEntity.badRequest().body(error);
            }
            
            if (request.role() == null || request.role().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El rol es requerido");
                return ResponseEntity.badRequest().body(error);
            }

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

            logger.info("User registered successfully with ID: {}", user.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error during registration: {}", e.getMessage(), e);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            logger.info("Login attempt for user: {}", request.email());
            
            LoginCommand command = new LoginCommand(request.email(), request.password());
            Optional<Object> userOpt = authenticationService.login(command);

            if (userOpt.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Credenciales inválidas");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }

            Object user = userOpt.get();
            AuthResponse response = mapEntityToAuthResponse(user);

            logger.info("Login successful for user: {}", request.email());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error during login: {}", e.getMessage(), e);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error en el login: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
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
