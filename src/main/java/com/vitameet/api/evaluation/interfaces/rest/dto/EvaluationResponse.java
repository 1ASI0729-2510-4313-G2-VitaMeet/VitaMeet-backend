package com.vitameet.api.evaluation.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vitameet.api.evaluation.domain.model.Evaluation;

import java.time.LocalDateTime;

public class EvaluationResponse {
    
    private Long id;
    private Long patientId;
    private Long doctorId;
    private Integer rating;
    private String comment;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime evaluationDate;
    
    // Constructors
    public EvaluationResponse() {
    }
    
    public EvaluationResponse(Evaluation evaluation) {
        this.id = evaluation.getId();
        this.patientId = evaluation.getPatientId();
        this.doctorId = evaluation.getDoctorId();
        this.rating = evaluation.getRating();
        this.comment = evaluation.getComment();
        this.evaluationDate = evaluation.getEvaluationDate();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getPatientId() {
        return patientId;
    }
    
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    
    public Long getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public LocalDateTime getEvaluationDate() {
        return evaluationDate;
    }
    
    public void setEvaluationDate(LocalDateTime evaluationDate) {
        this.evaluationDate = evaluationDate;
    }
}
