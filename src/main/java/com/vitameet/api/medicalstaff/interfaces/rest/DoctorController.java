package com.vitameet.api.medicalstaff.interfaces.rest;

import com.vitameet.api.medicalstaff.application.commands.CreateDoctorCommand;
import com.vitameet.api.medicalstaff.application.commands.UpdateDoctorCommand;
import com.vitameet.api.medicalstaff.application.services.DoctorCommandService;
import com.vitameet.api.medicalstaff.domain.model.Doctor;
import com.vitameet.api.medicalstaff.interfaces.rest.dto.CreateDoctorRequest;
import com.vitameet.api.medicalstaff.interfaces.rest.dto.DoctorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/doctors")
@Tag(name = "Doctors", description = "API para gestión de médicos")
public class DoctorController {
    
    private final DoctorCommandService doctorCommandService;

    public DoctorController(DoctorCommandService doctorCommandService) {
        this.doctorCommandService = doctorCommandService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los médicos")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        List<Doctor> doctors = doctorCommandService.getAllDoctors();
        List<DoctorResponse> response = doctors.stream()
                .map(DoctorResponse::from)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo médico")
    public ResponseEntity<?> createDoctor(@RequestBody CreateDoctorRequest request) {
        try {
            CreateDoctorCommand command = new CreateDoctorCommand(
                request.specialty(),
                request.medicalLicense(),
                request.officeLocation()
            );

            Doctor doctor = doctorCommandService.handle(command);
            return ResponseEntity.ok(DoctorResponse.from(doctor));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar médico")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody CreateDoctorRequest request) {
        try {
            UpdateDoctorCommand command = new UpdateDoctorCommand(
                id,
                request.specialty(),
                request.officeLocation()
            );

            Doctor doctor = doctorCommandService.handle(command);
            return ResponseEntity.ok(DoctorResponse.from(doctor));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar médico")
    public ResponseEntity<Map<String, String>> deleteDoctor(@PathVariable Long id) {
        try {
            doctorCommandService.deleteDoctor(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Médico eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
