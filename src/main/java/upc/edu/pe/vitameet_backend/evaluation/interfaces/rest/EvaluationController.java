package upc.edu.pe.vitameet_backend.evaluation.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.vitameet_backend.evaluation.application.internal.commandservices.SubmitEvaluationService;
import upc.edu.pe.vitameet_backend.evaluation.domain.model.aggregates.Evaluation;
import upc.edu.pe.vitameet_backend.evaluation.infrastructure.persistence.jpa.repositories.EvaluationRepository;
import upc.edu.pe.vitameet_backend.evaluation.interfaces.rest.resource.SubmitEvaluationResource;
import upc.edu.pe.vitameet_backend.evaluation.interfaces.rest.transform.EvaluationResourceAssembler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {

    private final SubmitEvaluationService submitService;
    private final EvaluationRepository repository;

    public EvaluationController(SubmitEvaluationService submitService, EvaluationRepository repository) {
        this.submitService = submitService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Evaluation> submit(@RequestBody SubmitEvaluationResource resource) {
        var command = EvaluationResourceAssembler.toCommandFromResource(resource);
        return ResponseEntity.ok(submitService.handle(command));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Evaluation>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(repository.findAllByDoctorId(doctorId));
    }
}
