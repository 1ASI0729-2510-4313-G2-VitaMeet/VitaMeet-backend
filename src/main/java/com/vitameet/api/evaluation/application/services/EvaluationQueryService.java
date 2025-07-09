package com.vitameet.api.evaluation.application.services;

import com.vitameet.api.evaluation.application.queries.GetEvaluationByIdQuery;
import com.vitameet.api.evaluation.application.queries.GetEvaluationsByDoctorIdQuery;
import com.vitameet.api.evaluation.application.queries.GetDoctorAverageRatingQuery;
import com.vitameet.api.evaluation.domain.model.Evaluation;
import com.vitameet.api.evaluation.domain.repositories.EvaluationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EvaluationQueryService {
    
    private final EvaluationRepository evaluationRepository;
    
    public EvaluationQueryService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }
    
    public Optional<Evaluation> getEvaluationById(GetEvaluationByIdQuery query) {
        return evaluationRepository.findById(query.getEvaluationId());
    }
    
    public List<Evaluation> getEvaluationsByDoctorId(GetEvaluationsByDoctorIdQuery query) {
        return evaluationRepository.findByDoctorIdOrderByEvaluationDateDesc(query.getDoctorId());
    }
    
    public Double getDoctorAverageRating(GetDoctorAverageRatingQuery query) {
        return evaluationRepository.findAverageRatingByDoctorId(query.getDoctorId());
    }
    
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }
    
    public List<Evaluation> getEvaluationsByPatientId(Long patientId) {
        return evaluationRepository.findByPatientId(patientId);
    }
    
    public List<Evaluation> getEvaluationsByRating(Integer rating) {
        return evaluationRepository.findByRating(rating);
    }
}
