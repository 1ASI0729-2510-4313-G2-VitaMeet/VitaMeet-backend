package com.vitameet.api.medicalrecords.application.queries;

import com.vitameet.api.shared.domain.Query;

public class GetMedicalRecordsByPatientIdQuery implements Query {
    private final Long patientId;
    
    public GetMedicalRecordsByPatientIdQuery(Long patientId) {
        this.patientId = patientId;
    }
    
    public Long getPatientId() {
        return patientId;
    }
}
