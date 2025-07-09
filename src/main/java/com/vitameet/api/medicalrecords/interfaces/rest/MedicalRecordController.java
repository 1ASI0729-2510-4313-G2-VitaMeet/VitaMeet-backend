package com.vitameet.api.medicalrecords.interfaces.rest;

import com.vitameet.api.medicalrecords.application.commands.CreateMedicalRecordCommand;
import com.vitameet.api.medicalrecords.application.commands.UpdateMedicalRecordCommand;
import com.vitameet.api.medicalrecords.application.queries.GetMedicalRecordByIdQuery;
import com.vitameet.api.medicalrecords.application.queries.GetMedicalRecordsByPatientIdQuery;
import com.vitameet.api.medicalrecords.application.queries.GetMedicalRecordsByDoctorIdQuery;
import com.vitameet.api.medicalrecords.application.services.MedicalRecordCommandService;
import com.vitameet.api.medicalrecords.application.services.MedicalRecordQueryService;
import com.vitameet.api.medicalrecords.domain.model.MedicalRecord;
import com.vitameet.api.medicalrecords.interfaces.rest.dto.CreateMedicalRecordRequest;
import com.vitameet.api.medicalrecords.interfaces.rest.dto.UpdateMedicalRecordRequest;
import com.vitameet.api.medicalrecords.interfaces.rest.dto.MedicalRecordResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medical-records")
@Tag(name = "Medical Records", description = "API para gestión de historiales médicos")
public class MedicalRecordController {
    
    private final MedicalRecordCommandService medicalRecordCommandService;
    private final MedicalRecordQueryService medicalRecordQueryService;
    
    public MedicalRecordController(MedicalRecordCommandService medicalRecordCommandService,
                                  MedicalRecordQueryService medicalRecordQueryService) {
        this.medicalRecordCommandService = medicalRecordCommandService;
        this.medicalRecordQueryService = medicalRecordQueryService;
    }
    
    @GetMapping
    @Operation(summary = "Listar todos los historiales médicos")
    public ResponseEntity<List<MedicalRecordResponse>> getAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordQueryService.getAllMedicalRecords();
        List<MedicalRecordResponse> response = medicalRecords.stream()
            .map(MedicalRecordResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener historial médico por ID")
    public ResponseEntity<MedicalRecordResponse> getMedicalRecordById(@PathVariable Long id) {
        return medicalRecordQueryService.getMedicalRecordById(new GetMedicalRecordByIdQuery(id))
            .map(medicalRecord -> ResponseEntity.ok(new MedicalRecordResponse(medicalRecord)))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Obtener historiales médicos por paciente")
    public ResponseEntity<List<MedicalRecordResponse>> getMedicalRecordsByPatientId(@PathVariable Long patientId) {
        List<MedicalRecord> medicalRecords = medicalRecordQueryService
            .getMedicalRecordsByPatientId(new GetMedicalRecordsByPatientIdQuery(patientId));
        List<MedicalRecordResponse> response = medicalRecords.stream()
            .map(MedicalRecordResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Obtener historiales médicos por médico")
    public ResponseEntity<List<MedicalRecordResponse>> getMedicalRecordsByDoctorId(@PathVariable Long doctorId) {
        List<MedicalRecord> medicalRecords = medicalRecordQueryService
            .getMedicalRecordsByDoctorId(new GetMedicalRecordsByDoctorIdQuery(doctorId));
        List<MedicalRecordResponse> response = medicalRecords.stream()
            .map(MedicalRecordResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/appointment/{appointmentId}")
    @Operation(summary = "Obtener historiales médicos por cita")
    public ResponseEntity<List<MedicalRecordResponse>> getMedicalRecordsByAppointmentId(@PathVariable Long appointmentId) {
        List<MedicalRecord> medicalRecords = medicalRecordQueryService.getMedicalRecordsByAppointmentId(appointmentId);
        List<MedicalRecordResponse> response = medicalRecords.stream()
            .map(MedicalRecordResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    @Operation(summary = "Crear nuevo historial médico")
    public ResponseEntity<?> createMedicalRecord(@Valid @RequestBody CreateMedicalRecordRequest request) {
        try {
            CreateMedicalRecordCommand command = new CreateMedicalRecordCommand(
                request.getPatientId(),
                request.getDoctorId(),
                request.getAppointmentId(),
                request.getDiagnosis(),
                request.getTreatment(),
                request.getObservations()
            );
            
            MedicalRecord medicalRecord = medicalRecordCommandService.createMedicalRecord(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MedicalRecordResponse(medicalRecord));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear el historial médico: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar historial médico")
    public ResponseEntity<?> updateMedicalRecord(@PathVariable Long id, 
                                                 @Valid @RequestBody UpdateMedicalRecordRequest request) {
        try {
            UpdateMedicalRecordCommand command = new UpdateMedicalRecordCommand(
                id,
                request.getDiagnosis(),
                request.getTreatment(),
                request.getObservations()
            );
            
            MedicalRecord medicalRecord = medicalRecordCommandService.updateMedicalRecord(command);
            return ResponseEntity.ok(new MedicalRecordResponse(medicalRecord));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar el historial médico: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar historial médico")
    public ResponseEntity<?> deleteMedicalRecord(@PathVariable Long id) {
        try {
            medicalRecordCommandService.deleteMedicalRecord(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Historial médico eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
