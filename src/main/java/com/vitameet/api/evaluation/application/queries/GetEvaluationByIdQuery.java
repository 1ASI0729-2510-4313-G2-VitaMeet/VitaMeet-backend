package com.vitameet.api.evaluation.application.queries;

import com.vitameet.api.shared.domain.Query;

public class GetEvaluationByIdQuery implements Query {
    private final Long evaluationId;
    
    public GetEvaluationByIdQuery(Long evaluationId) {
        this.evaluationId = evaluationId;
    }
    
    public Long getEvaluationId() {
        return evaluationId;
    }
}
