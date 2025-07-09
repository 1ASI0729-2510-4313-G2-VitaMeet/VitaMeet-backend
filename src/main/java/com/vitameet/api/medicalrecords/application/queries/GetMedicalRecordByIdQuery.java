package com.vitameet.api.medicalrecords.application.queries;

import com.vitameet.api.shared.domain.Query;

public class GetMedicalRecordByIdQuery implements Query {
    private final Long medicalRecordId;
    
    public GetMedicalRecordByIdQuery(Long medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }
    
    public Long getMedicalRecordId() {
        return medicalRecordId;
    }
}
