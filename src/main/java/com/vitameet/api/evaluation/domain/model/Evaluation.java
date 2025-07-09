package com.vitameet.api.evaluation.domain.model;

import com.vitameet.api.shared.domain.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluaciones")
public class Evaluation extends BaseEntity {
    
    @Column(name = "paciente_id", nullable = false)
    private Long patientId;
    
    @Column(name = "medico_id", nullable = false)
    private Long doctorId;
    
    @Column(name = "puntuacion", nullable = false)
    private Integer rating; // From 1 to 5
    
    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comment;
    
    @Column(name = "fecha", nullable = false)
    private LocalDateTime evaluationDate;
    
    // Constructors
    protected Evaluation() {
    }
    
    public Evaluation(Long patientId, Long doctorId, Integer rating, String comment) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.rating = validateRating(rating);
        this.comment = comment;
        this.evaluationDate = LocalDateTime.now();
    }
    
    // Business methods
    public void updateEvaluation(Integer rating, String comment) {
        this.rating = validateRating(rating);
        this.comment = comment;
    }
    
    private Integer validateRating(Integer rating) {
        if (rating == null || rating < 1 || rating > 5) {
            throw new IllegalArgumentException("La puntuación debe estar entre 1 y 5");
        }
        return rating;
    }
    
    public boolean isPositiveEvaluation() {
        return rating >= 4;
    }
    
    public boolean hasComment() {
        return comment != null && !comment.trim().isEmpty();
    }
    
    // Getters
    public Long getPatientId() {
        return patientId;
    }
    
    public Long getDoctorId() {
        return doctorId;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public String getComment() {
        return comment;
    }
    
    public LocalDateTime getEvaluationDate() {
        return evaluationDate;
    }
}
