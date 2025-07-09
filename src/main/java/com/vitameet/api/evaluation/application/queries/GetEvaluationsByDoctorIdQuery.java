package com.vitameet.api.evaluation.application.queries;

import com.vitameet.api.shared.domain.Query;

public class GetEvaluationsByDoctorIdQuery implements Query {
    private final Long doctorId;
    
    public GetEvaluationsByDoctorIdQuery(Long doctorId) {
        this.doctorId = doctorId;
    }
    
    public Long getDoctorId() {
        return doctorId;
    }
}
