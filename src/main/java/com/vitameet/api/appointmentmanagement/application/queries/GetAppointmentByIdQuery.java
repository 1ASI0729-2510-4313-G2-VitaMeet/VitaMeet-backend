package com.vitameet.api.appointmentmanagement.application.queries;

import com.vitameet.api.shared.domain.Query;

public class GetAppointmentByIdQuery implements Query {
    private final Long appointmentId;
    
    public GetAppointmentByIdQuery(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
    
    public Long getAppointmentId() {
        return appointmentId;
    }
}
