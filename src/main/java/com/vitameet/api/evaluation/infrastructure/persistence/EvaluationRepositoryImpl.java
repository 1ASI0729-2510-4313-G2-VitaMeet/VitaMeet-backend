package com.vitameet.api.evaluation.infrastructure.persistence;

import com.vitameet.api.evaluation.domain.model.Evaluation;
import com.vitameet.api.evaluation.domain.repositories.EvaluationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EvaluationRepositoryImpl implements EvaluationRepository {
    
    private final JpaEvaluationRepository jpaEvaluationRepository;
    
    public EvaluationRepositoryImpl(JpaEvaluationRepository jpaEvaluationRepository) {
        this.jpaEvaluationRepository = jpaEvaluationRepository;
    }
    
    @Override
    public Evaluation save(Evaluation evaluation) {
        return jpaEvaluationRepository.save(evaluation);
    }
    
    @Override
    public Optional<Evaluation> findById(Long id) {
        return jpaEvaluationRepository.findById(id);
    }
    
    @Override
    public List<Evaluation> findAll() {
        return jpaEvaluationRepository.findAll();
    }
    
    @Override
    public void delete(Evaluation evaluation) {
        jpaEvaluationRepository.delete(evaluation);
    }
    
    @Override
    public void deleteById(Long id) {
        jpaEvaluationRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaEvaluationRepository.existsById(id);
    }
    
    @Override
    public List<Evaluation> findByPatientId(Long patientId) {
        return jpaEvaluationRepository.findByPatientId(patientId);
    }
    
    @Override
    public List<Evaluation> findByDoctorId(Long doctorId) {
        return jpaEvaluationRepository.findByDoctorId(doctorId);
    }
    
    @Override
    public List<Evaluation> findByDoctorIdOrderByEvaluationDateDesc(Long doctorId) {
        return jpaEvaluationRepository.findByDoctorIdOrderByEvaluationDateDesc(doctorId);
    }
    
    @Override
    public Double findAverageRatingByDoctorId(Long doctorId) {
        return jpaEvaluationRepository.findAverageRatingByDoctorId(doctorId);
    }
    
    @Override
    public List<Evaluation> findByRating(Integer rating) {
        return jpaEvaluationRepository.findByRating(rating);
    }
}
