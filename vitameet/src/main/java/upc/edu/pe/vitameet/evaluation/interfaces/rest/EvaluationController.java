package upc.edu.pe.vitameet.evaluation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.vitameet.evaluation.application.internal.commandservices.EvaluationCommandServiceImpl;
import upc.edu.pe.vitameet.evaluation.domain.model.commands.CreateEvaluationCommand;
import upc.edu.pe.vitameet.evaluation.interfaces.rest.resource.EvaluationResource;
import upc.edu.pe.vitameet.evaluation.interfaces.rest.transform.EvaluationResourceFromEntityAssembler;

@RestController
@RequestMapping("/api/v1/evaluations")
@RequiredArgsConstructor
@Tag(name = "Evaluations", description = "API for managing appointment evaluations")
public class EvaluationController {

    private final EvaluationCommandServiceImpl evaluationCommandService;

    @PostMapping
    public ResponseEntity<EvaluationResource> createEvaluation(@RequestBody CreateEvaluationCommand command) {
        var evaluation = evaluationCommandService.handle(command);
        var resource = EvaluationResourceFromEntityAssembler.toResource(evaluation);
        return ResponseEntity.ok(resource);
    }
}
