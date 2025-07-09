package com.vitameet.api.appointmentmanagement.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vitameet.api.appointmentmanagement.domain.model.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateAppointmentRequest {
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;
    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime appointmentTime;
    
    private String reason;
    
    private Appointment.AppointmentStatus status;
    
    // Constructors
    public UpdateAppointmentRequest() {
    }
    
    public UpdateAppointmentRequest(LocalDate appointmentDate, LocalTime appointmentTime, 
                                   String reason, Appointment.AppointmentStatus status) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.status = status;
    }
    
    // Getters and Setters
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
    
    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }
    
    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public Appointment.AppointmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(Appointment.AppointmentStatus status) {
        this.status = status;
    }
}
