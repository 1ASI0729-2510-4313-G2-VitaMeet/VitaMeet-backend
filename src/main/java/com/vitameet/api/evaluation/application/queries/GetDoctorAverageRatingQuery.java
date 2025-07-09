package com.vitameet.api.evaluation.application.queries;

import com.vitameet.api.shared.domain.Query;

public class GetDoctorAverageRatingQuery implements Query {
    private final Long doctorId;
    
    public GetDoctorAverageRatingQuery(Long doctorId) {
        this.doctorId = doctorId;
    }
    
    public Long getDoctorId() {
        return doctorId;
    }
}
