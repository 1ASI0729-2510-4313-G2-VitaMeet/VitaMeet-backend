package upc.edu.pe.vitameet.evaluation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet.evaluation.domain.model.aggregates.Evaluation;
import upc.edu.pe.vitameet.evaluation.domain.model.commands.CreateEvaluationCommand;
import upc.edu.pe.vitameet.evaluation.domain.services.EvaluationCommandService;
import upc.edu.pe.vitameet.evaluation.infrastructure.persistence.jpa.repositories.EvaluationRepository;

@Service
public class EvaluationCommandServiceImpl implements EvaluationCommandService {

    private final EvaluationRepository repository;

    public EvaluationCommandServiceImpl(EvaluationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Evaluation handle(CreateEvaluationCommand command) {
        Evaluation evaluation = new Evaluation(command);
        return repository.save(evaluation);
    }
}
