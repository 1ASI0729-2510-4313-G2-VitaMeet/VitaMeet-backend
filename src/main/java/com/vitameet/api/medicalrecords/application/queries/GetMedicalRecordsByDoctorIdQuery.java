package com.vitameet.api.medicalrecords.application.queries;

import com.vitameet.api.shared.domain.Query;

public class GetMedicalRecordsByDoctorIdQuery implements Query {
    private final Long doctorId;
    
    public GetMedicalRecordsByDoctorIdQuery(Long doctorId) {
        this.doctorId = doctorId;
    }
    
    public Long getDoctorId() {
        return doctorId;
    }
}
