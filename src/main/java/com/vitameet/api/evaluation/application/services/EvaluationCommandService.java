package com.vitameet.api.evaluation.application.services;

import com.vitameet.api.evaluation.application.commands.CreateEvaluationCommand;
import com.vitameet.api.evaluation.application.commands.UpdateEvaluationCommand;
import com.vitameet.api.evaluation.domain.model.Evaluation;
import com.vitameet.api.evaluation.domain.repositories.EvaluationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EvaluationCommandService {
    
    private final EvaluationRepository evaluationRepository;
    
    public EvaluationCommandService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }
    
    public Evaluation createEvaluation(CreateEvaluationCommand command) {
        var evaluation = new Evaluation(
            command.getPatientId(),
            command.getDoctorId(),
            command.getRating(),
            command.getComment()
        );
        
        return evaluationRepository.save(evaluation);
    }
    
    public Evaluation updateEvaluation(UpdateEvaluationCommand command) {
        var evaluation = evaluationRepository.findById(command.getEvaluationId())
            .orElseThrow(() -> new IllegalArgumentException("Evaluación no encontrada"));
        
        evaluation.updateEvaluation(command.getRating(), command.getComment());
        
        return evaluationRepository.save(evaluation);
    }
    
    public void deleteEvaluation(Long evaluationId) {
        if (!evaluationRepository.existsById(evaluationId)) {
            throw new IllegalArgumentException("Evaluación no encontrada");
        }
        evaluationRepository.deleteById(evaluationId);
    }
}
