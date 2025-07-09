package com.vitameet.api.appointmentmanagement.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vitameet.api.appointmentmanagement.domain.model.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentResponse {
    
    private Long id;
    private Long patientId;
    private Long doctorId;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;
    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime appointmentTime;
    
    private String reason;
    private Appointment.AppointmentStatus status;
    
    // Constructors
    public AppointmentResponse() {
    }
    
    public AppointmentResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.patientId = appointment.getPatientId();
        this.doctorId = appointment.getDoctorId();
        this.appointmentDate = appointment.getAppointmentDate();
        this.appointmentTime = appointment.getAppointmentTime();
        this.reason = appointment.getReason();
        this.status = appointment.getStatus();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getPatientId() {
        return patientId;
    }
    
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    
    public Long getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
    
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
