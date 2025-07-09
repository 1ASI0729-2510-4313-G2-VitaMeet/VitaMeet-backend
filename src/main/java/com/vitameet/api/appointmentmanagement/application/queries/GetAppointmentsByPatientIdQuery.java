package com.vitameet.api.appointmentmanagement.application.queries;

import com.vitameet.api.shared.domain.Query;

public class GetAppointmentsByPatientIdQuery implements Query {
    private final Long patientId;
    
    public GetAppointmentsByPatientIdQuery(Long patientId) {
        this.patientId = patientId;
    }
    
    public Long getPatientId() {
        return patientId;
    }
}
