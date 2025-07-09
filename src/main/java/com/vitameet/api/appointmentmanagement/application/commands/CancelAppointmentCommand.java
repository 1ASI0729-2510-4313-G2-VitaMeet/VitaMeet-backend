package com.vitameet.api.appointmentmanagement.application.commands;

import com.vitameet.api.shared.domain.Command;

public class CancelAppointmentCommand implements Command {
    private final Long appointmentId;
    
    public CancelAppointmentCommand(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
    
    public Long getAppointmentId() {
        return appointmentId;
    }
}
