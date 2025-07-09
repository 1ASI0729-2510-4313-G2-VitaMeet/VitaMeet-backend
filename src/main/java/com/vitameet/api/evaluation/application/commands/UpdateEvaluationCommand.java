package com.vitameet.api.evaluation.application.commands;

import com.vitameet.api.shared.domain.Command;

public class UpdateEvaluationCommand implements Command {
    private final Long evaluationId;
    private final Integer rating;
    private final String comment;
    
    public UpdateEvaluationCommand(Long evaluationId, Integer rating, String comment) {
        this.evaluationId = evaluationId;
        this.rating = rating;
        this.comment = comment;
    }
    
    public Long getEvaluationId() {
        return evaluationId;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public String getComment() {
        return comment;
    }
}
