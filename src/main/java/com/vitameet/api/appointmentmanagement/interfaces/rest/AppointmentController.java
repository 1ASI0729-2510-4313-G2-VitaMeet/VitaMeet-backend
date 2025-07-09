package com.vitameet.api.appointmentmanagement.interfaces.rest;

import com.vitameet.api.appointmentmanagement.application.commands.CreateAppointmentCommand;
import com.vitameet.api.appointmentmanagement.application.commands.UpdateAppointmentCommand;
import com.vitameet.api.appointmentmanagement.application.commands.CancelAppointmentCommand;
import com.vitameet.api.appointmentmanagement.application.queries.GetAppointmentByIdQuery;
import com.vitameet.api.appointmentmanagement.application.queries.GetAppointmentsByPatientIdQuery;
import com.vitameet.api.appointmentmanagement.application.queries.GetAppointmentsByDoctorIdQuery;
import com.vitameet.api.appointmentmanagement.application.services.AppointmentCommandService;
import com.vitameet.api.appointmentmanagement.application.services.AppointmentQueryService;
import com.vitameet.api.appointmentmanagement.domain.model.Appointment;
import com.vitameet.api.appointmentmanagement.interfaces.rest.dto.CreateAppointmentRequest;
import com.vitameet.api.appointmentmanagement.interfaces.rest.dto.UpdateAppointmentRequest;
import com.vitameet.api.appointmentmanagement.interfaces.rest.dto.AppointmentResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointments", description = "API para gestión de citas médicas")
public class AppointmentController {
    
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;
    
    public AppointmentController(AppointmentCommandService appointmentCommandService,
                                AppointmentQueryService appointmentQueryService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }
    
    @GetMapping
    @Operation(summary = "Listar todas las citas")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<Appointment> appointments = appointmentQueryService.getAllAppointments();
        List<AppointmentResponse> response = appointments.stream()
            .map(AppointmentResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener cita por ID")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id) {
        return appointmentQueryService.getAppointmentById(new GetAppointmentByIdQuery(id))
            .map(appointment -> ResponseEntity.ok(new AppointmentResponse(appointment)))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Obtener citas por paciente")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        List<Appointment> appointments = appointmentQueryService
            .getAppointmentsByPatientId(new GetAppointmentsByPatientIdQuery(patientId));
        List<AppointmentResponse> response = appointments.stream()
            .map(AppointmentResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Obtener citas por médico")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        List<Appointment> appointments = appointmentQueryService
            .getAppointmentsByDoctorId(new GetAppointmentsByDoctorIdQuery(doctorId));
        List<AppointmentResponse> response = appointments.stream()
            .map(AppointmentResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/doctor/{doctorId}/date/{date}")
    @Operation(summary = "Obtener citas por médico y fecha")
    public ResponseEntity<List<AppointmentResponse>> getDoctorAppointmentsByDate(
            @PathVariable Long doctorId, @PathVariable String date) {
        try {
            LocalDate appointmentDate = LocalDate.parse(date);
            List<Appointment> appointments = appointmentQueryService
                .getDoctorAppointmentsByDate(doctorId, appointmentDate);
            List<AppointmentResponse> response = appointments.stream()
                .map(AppointmentResponse::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/date/{date}")
    @Operation(summary = "Obtener citas por fecha")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDate(@PathVariable String date) {
        try {
            LocalDate appointmentDate = LocalDate.parse(date);
            List<Appointment> appointments = appointmentQueryService.getAppointmentsByDate(appointmentDate);
            List<AppointmentResponse> response = appointments.stream()
                .map(AppointmentResponse::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "Obtener citas por estado")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByStatus(@PathVariable String status) {
        try {
            Appointment.AppointmentStatus appointmentStatus = Appointment.AppointmentStatus.valueOf(status.toUpperCase());
            List<Appointment> appointments = appointmentQueryService.getAppointmentsByStatus(appointmentStatus);
            List<AppointmentResponse> response = appointments.stream()
                .map(AppointmentResponse::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping
    @Operation(summary = "Crear nueva cita")
    public ResponseEntity<?> createAppointment(@Valid @RequestBody CreateAppointmentRequest request) {
        try {
            CreateAppointmentCommand command = new CreateAppointmentCommand(
                request.getPatientId(),
                request.getDoctorId(),
                request.getAppointmentDate(),
                request.getAppointmentTime(),
                request.getReason()
            );
            
            Appointment appointment = appointmentCommandService.createAppointment(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AppointmentResponse(appointment));
        } catch (IllegalStateException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear la cita: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cita")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, 
                                              @Valid @RequestBody UpdateAppointmentRequest request) {
        try {
            UpdateAppointmentCommand command = new UpdateAppointmentCommand(
                id,
                request.getAppointmentDate(),
                request.getAppointmentTime(),
                request.getReason(),
                request.getStatus()
            );
            
            Appointment appointment = appointmentCommandService.updateAppointment(command);
            return ResponseEntity.ok(new AppointmentResponse(appointment));
        } catch (IllegalArgumentException | IllegalStateException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar la cita: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}/cancel")
    @Operation(summary = "Cancelar cita")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long id) {
        try {
            appointmentCommandService.cancelAppointment(new CancelAppointmentCommand(id));
            Map<String, String> response = new HashMap<>();
            response.put("message", "Cita cancelada exitosamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException | IllegalStateException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}/complete")
    @Operation(summary = "Completar cita")
    public ResponseEntity<?> completeAppointment(@PathVariable Long id) {
        try {
            appointmentCommandService.completeAppointment(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Cita completada exitosamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException | IllegalStateException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cita")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentCommandService.deleteAppointment(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Cita eliminada exitosamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
