package com.vitameet.api.appointmentmanagement.application.commands;

import com.vitameet.api.shared.domain.Command;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAppointmentCommand implements Command {
    private final Long patientId;
    private final Long doctorId;
    private final LocalDate appointmentDate;
    private final LocalTime appointmentTime;
    private final String reason;
    
    public CreateAppointmentCommand(Long patientId, Long doctorId, LocalDate appointmentDate, 
                                   LocalTime appointmentTime, String reason) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
    }
    
    public Long getPatientId() {
        return patientId;
    }
    
    public Long getDoctorId() {
        return doctorId;
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
}
