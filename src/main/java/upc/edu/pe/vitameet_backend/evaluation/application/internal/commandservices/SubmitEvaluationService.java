package upc.edu.pe.vitameet_backend.evaluation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.edu.pe.vitameet_backend.evaluation.domain.model.aggregates.Evaluation;
import upc.edu.pe.vitameet_backend.evaluation.domain.model.commands.SubmitEvaluationCommand;
import upc.edu.pe.vitameet_backend.evaluation.domain.model.valueobjects.Rating;
import upc.edu.pe.vitameet_backend.evaluation.infrastructure.persistence.jpa.repositories.EvaluationRepository;

@Service
public class SubmitEvaluationService {

    private final EvaluationRepository repository;

    public SubmitEvaluationService(EvaluationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Evaluation handle(SubmitEvaluationCommand command) {
        var evaluation = new Evaluation(
                new Rating(command.rating()),
                command.comment(),
                command.doctorId(),
                command.patientId()
        );
        return repository.save(evaluation);
    }
}
