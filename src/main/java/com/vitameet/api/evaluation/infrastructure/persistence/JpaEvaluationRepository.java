package com.vitameet.api.evaluation.infrastructure.persistence;

import com.vitameet.api.evaluation.domain.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaEvaluationRepository extends JpaRepository<Evaluation, Long> {
    
    List<Evaluation> findByPatientId(Long patientId);
    
    List<Evaluation> findByDoctorId(Long doctorId);
    
    List<Evaluation> findByRating(Integer rating);
    
    @Query("SELECT e FROM Evaluation e WHERE e.doctorId = :doctorId ORDER BY e.evaluationDate DESC")
    List<Evaluation> findByDoctorIdOrderByEvaluationDateDesc(@Param("doctorId") Long doctorId);
    
    @Query("SELECT AVG(e.rating) FROM Evaluation e WHERE e.doctorId = :doctorId")
    Double findAverageRatingByDoctorId(@Param("doctorId") Long doctorId);
}
