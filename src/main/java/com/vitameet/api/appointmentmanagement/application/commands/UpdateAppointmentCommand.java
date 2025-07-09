package com.vitameet.api.appointmentmanagement.application.commands;

import com.vitameet.api.shared.domain.Command;
import com.vitameet.api.appointmentmanagement.domain.model.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateAppointmentCommand implements Command {
    private final Long appointmentId;
    private final LocalDate appointmentDate;
    private final LocalTime appointmentTime;
    private final String reason;
    private final Appointment.AppointmentStatus status;
    
    public UpdateAppointmentCommand(Long appointmentId, LocalDate appointmentDate, 
                                   LocalTime appointmentTime, String reason, 
                                   Appointment.AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.status = status;
    }
    
    public Long getAppointmentId() {
        return appointmentId;
    }
    
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
    
    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }
    
    public String getReason() {
        return reason;
    }
    
    public Appointment.AppointmentStatus getStatus() {
        return status;
    }
}
