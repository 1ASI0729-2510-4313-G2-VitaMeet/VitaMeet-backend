package upc.edu.pe.vitameet.evaluation.domain.services;

import upc.edu.pe.vitameet.evaluation.domain.model.aggregates.Evaluation;
import upc.edu.pe.vitameet.evaluation.domain.model.commands.CreateEvaluationCommand;

public interface EvaluationCommandService {
    Evaluation handle(CreateEvaluationCommand command);
}
