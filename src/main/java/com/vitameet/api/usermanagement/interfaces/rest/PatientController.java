package com.vitameet.api.usermanagement.interfaces.rest;

import com.vitameet.api.usermanagement.application.commands.CreatePatientCommand;
import com.vitameet.api.usermanagement.application.commands.UpdatePatientCommand;
import com.vitameet.api.usermanagement.application.commands.LoginPatientCommand;
import com.vitameet.api.usermanagement.application.queries.GetPatientByIdQuery;
import com.vitameet.api.usermanagement.application.queries.GetPatientByDniQuery;
import com.vitameet.api.usermanagement.application.services.PatientCommandService;
import com.vitameet.api.usermanagement.application.services.PatientQueryService;
import com.vitameet.api.usermanagement.domain.model.Patient;
import com.vitameet.api.usermanagement.interfaces.rest.dto.CreatePatientRequest;
import com.vitameet.api.usermanagement.interfaces.rest.dto.UpdatePatientRequest;
import com.vitameet.api.usermanagement.interfaces.rest.dto.LoginRequest;
import com.vitameet.api.usermanagement.interfaces.rest.dto.PatientResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patients")
@Tag(name = "Patients", description = "API para gestión de pacientes")
public class PatientController {

    private final PatientCommandService patientCommandService;
    private final PatientQueryService patientQueryService;

    public PatientController(PatientCommandService patientCommandService, 
                           PatientQueryService patientQueryService) {
        this.patientCommandService = patientCommandService;
        this.patientQueryService = patientQueryService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener paciente por ID")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
        GetPatientByIdQuery query = new GetPatientByIdQuery(id);
        return patientQueryService.handle(query)
                .map(patient -> ResponseEntity.ok(PatientResponse.from(patient)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dni/{dni}")
    @Operation(summary = "Obtener paciente por DNI")
    public ResponseEntity<PatientResponse> getPatientByDni(@PathVariable String dni) {
        GetPatientByDniQuery query = new GetPatientByDniQuery(dni);
        return patientQueryService.handle(query)
                .map(patient -> ResponseEntity.ok(PatientResponse.from(patient)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nuevo paciente")
    public ResponseEntity<?> createPatient(@Valid @RequestBody CreatePatientRequest request) {
        try {
            CreatePatientCommand command = new CreatePatientCommand(
                request.dni(),
                request.fullName(),
                request.email(),
                request.password(),
                request.birthDate(),
                request.gender()
            );

            Patient patient = patientCommandService.handle(command);
            PatientResponse response = PatientResponse.from(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear paciente: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar paciente")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, 
                                          @Valid @RequestBody UpdatePatientRequest request) {
        try {
            UpdatePatientCommand command = new UpdatePatientCommand(
                id,
                request.dni(),
                request.fullName(),
                request.email(),
                request.password(),
                request.birthDate(),
                request.gender()
            );

            Patient patient = patientCommandService.handle(command);
            PatientResponse response = PatientResponse.from(patient);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar paciente: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión de paciente")
    public ResponseEntity<?> loginPatient(@Valid @RequestBody LoginRequest request) {
        try {
            LoginPatientCommand command = new LoginPatientCommand(request.email(), request.password());

            Optional<Patient> patientOpt = patientCommandService.handle(command);
            if (patientOpt.isPresent()) {
                PatientResponse response = PatientResponse.from(patientOpt.get());
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Credenciales inválidas");
                return ResponseEntity.badRequest().body(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error en el login: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar paciente")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        try {
            patientCommandService.deletePatient(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Paciente eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
