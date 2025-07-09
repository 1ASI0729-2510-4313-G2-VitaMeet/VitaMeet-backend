package com.vitameet.api.evaluation.domain.repositories;

import com.vitameet.api.evaluation.domain.model.Evaluation;
import java.util.List;
import java.util.Optional;

public interface EvaluationRepository {
    Evaluation save(Evaluation evaluation);
    Optional<Evaluation> findById(Long id);
    List<Evaluation> findAll();
    void delete(Evaluation evaluation);
    void deleteById(Long id);
    boolean existsById(Long id);
    
    // Business-specific queries
    List<Evaluation> findByPatientId(Long patientId);
    List<Evaluation> findByDoctorId(Long doctorId);
    List<Evaluation> findByDoctorIdOrderByEvaluationDateDesc(Long doctorId);
    Double findAverageRatingByDoctorId(Long doctorId);
    List<Evaluation> findByRating(Integer rating);
}
