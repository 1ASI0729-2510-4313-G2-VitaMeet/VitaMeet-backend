package com.vitameet.api.appointmentmanagement.application.queries;

import com.vitameet.api.shared.domain.Query;

public class GetAppointmentsByDoctorIdQuery implements Query {
    private final Long doctorId;
    
    public GetAppointmentsByDoctorIdQuery(Long doctorId) {
        this.doctorId = doctorId;
    }
    
    public Long getDoctorId() {
        return doctorId;
    }
}
