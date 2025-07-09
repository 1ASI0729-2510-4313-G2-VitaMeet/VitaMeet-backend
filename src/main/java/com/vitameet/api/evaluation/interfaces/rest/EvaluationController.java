package com.vitameet.api.evaluation.interfaces.rest;

import com.vitameet.api.evaluation.application.commands.CreateEvaluationCommand;
import com.vitameet.api.evaluation.application.commands.UpdateEvaluationCommand;
import com.vitameet.api.evaluation.application.queries.GetEvaluationByIdQuery;
import com.vitameet.api.evaluation.application.queries.GetEvaluationsByDoctorIdQuery;
import com.vitameet.api.evaluation.application.queries.GetDoctorAverageRatingQuery;
import com.vitameet.api.evaluation.application.services.EvaluationCommandService;
import com.vitameet.api.evaluation.application.services.EvaluationQueryService;
import com.vitameet.api.evaluation.domain.model.Evaluation;
import com.vitameet.api.evaluation.interfaces.rest.dto.CreateEvaluationRequest;
import com.vitameet.api.evaluation.interfaces.rest.dto.UpdateEvaluationRequest;
import com.vitameet.api.evaluation.interfaces.rest.dto.EvaluationResponse;

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
@RequestMapping("/api/evaluations")
@Tag(name = "Evaluations", description = "API para gestión de evaluaciones de médicos")
public class EvaluationController {
    
    private final EvaluationCommandService evaluationCommandService;
    private final EvaluationQueryService evaluationQueryService;
    
    public EvaluationController(EvaluationCommandService evaluationCommandService,
                               EvaluationQueryService evaluationQueryService) {
        this.evaluationCommandService = evaluationCommandService;
        this.evaluationQueryService = evaluationQueryService;
    }
    
    @GetMapping
    @Operation(summary = "Listar todas las evaluaciones")
    public ResponseEntity<List<EvaluationResponse>> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationQueryService.getAllEvaluations();
        List<EvaluationResponse> response = evaluations.stream()
            .map(EvaluationResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener evaluación por ID")
    public ResponseEntity<EvaluationResponse> getEvaluationById(@PathVariable Long id) {
        return evaluationQueryService.getEvaluationById(new GetEvaluationByIdQuery(id))
            .map(evaluation -> ResponseEntity.ok(new EvaluationResponse(evaluation)))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Obtener evaluaciones por paciente")
    public ResponseEntity<List<EvaluationResponse>> getEvaluationsByPatientId(@PathVariable Long patientId) {
        List<Evaluation> evaluations = evaluationQueryService.getEvaluationsByPatientId(patientId);
        List<EvaluationResponse> response = evaluations.stream()
            .map(EvaluationResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Obtener evaluaciones por médico")
    public ResponseEntity<List<EvaluationResponse>> getEvaluationsByDoctorId(@PathVariable Long doctorId) {
        List<Evaluation> evaluations = evaluationQueryService
            .getEvaluationsByDoctorId(new GetEvaluationsByDoctorIdQuery(doctorId));
        List<EvaluationResponse> response = evaluations.stream()
            .map(EvaluationResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/doctor/{doctorId}/average")
    @Operation(summary = "Obtener promedio de evaluaciones de un médico")
    public ResponseEntity<Map<String, Object>> getDoctorAverageRating(@PathVariable Long doctorId) {
        Double averageRating = evaluationQueryService
            .getDoctorAverageRating(new GetDoctorAverageRatingQuery(doctorId));
        List<Evaluation> evaluations = evaluationQueryService
            .getEvaluationsByDoctorId(new GetEvaluationsByDoctorIdQuery(doctorId));
        
        Map<String, Object> response = new HashMap<>();
        response.put("doctorId", doctorId);
        response.put("averageRating", averageRating != null ? averageRating : 0.0);
        response.put("totalEvaluations", evaluations.size());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/rating/{rating}")
    @Operation(summary = "Obtener evaluaciones por puntuación")
    public ResponseEntity<List<EvaluationResponse>> getEvaluationsByRating(@PathVariable Integer rating) {
        try {
            if (rating < 1 || rating > 5) {
                return ResponseEntity.badRequest().build();
            }
            
            List<Evaluation> evaluations = evaluationQueryService.getEvaluationsByRating(rating);
            List<EvaluationResponse> response = evaluations.stream()
                .map(EvaluationResponse::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping
    @Operation(summary = "Crear nueva evaluación")
    public ResponseEntity<?> createEvaluation(@Valid @RequestBody CreateEvaluationRequest request) {
        try {
            CreateEvaluationCommand command = new CreateEvaluationCommand(
                request.getPatientId(),
                request.getDoctorId(),
                request.getRating(),
                request.getComment()
            );
            
            Evaluation evaluation = evaluationCommandService.createEvaluation(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(new EvaluationResponse(evaluation));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear la evaluación: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar evaluación")
    public ResponseEntity<?> updateEvaluation(@PathVariable Long id, 
                                              @Valid @RequestBody UpdateEvaluationRequest request) {
        try {
            UpdateEvaluationCommand command = new UpdateEvaluationCommand(
                id,
                request.getRating(),
                request.getComment()
            );
            
            Evaluation evaluation = evaluationCommandService.updateEvaluation(command);
            return ResponseEntity.ok(new EvaluationResponse(evaluation));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar la evaluación: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evaluación")
    public ResponseEntity<?> deleteEvaluation(@PathVariable Long id) {
        try {
            evaluationCommandService.deleteEvaluation(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Evaluación eliminada exitosamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
