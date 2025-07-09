package com.vitameet.api.evaluation.application.commands;

import com.vitameet.api.shared.domain.Command;

public class CreateEvaluationCommand implements Command {
    private final Long patientId;
    private final Long doctorId;
    private final Integer rating;
    private final String comment;
    
    public CreateEvaluationCommand(Long patientId, Long doctorId, Integer rating, String comment) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.rating = rating;
        this.comment = comment;
    }
    
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
}
