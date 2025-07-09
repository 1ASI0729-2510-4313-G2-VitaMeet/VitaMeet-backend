package com.vitameet.api.medicalrecords.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vitameet.api.medicalrecords.domain.model.MedicalRecord;

import java.time.LocalDateTime;

public class MedicalRecordResponse {
    
    private Long id;
    private Long patientId;
    private Long doctorId;
    private Long appointmentId;
    private String diagnosis;
    private String treatment;
    private String observations;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;
    
    // Constructors
    public MedicalRecordResponse() {
    }
    
    public MedicalRecordResponse(MedicalRecord medicalRecord) {
        this.id = medicalRecord.getId();
        this.patientId = medicalRecord.getPatientId();
        this.doctorId = medicalRecord.getDoctorId();
        this.appointmentId = medicalRecord.getAppointmentId();
        this.diagnosis = medicalRecord.getDiagnosis();
        this.treatment = medicalRecord.getTreatment();
        this.observations = medicalRecord.getObservations();
        this.registrationDate = medicalRecord.getRegistrationDate();
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
    
    public Long getAppointmentId() {
        return appointmentId;
    }
    
    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
    
    public String getDiagnosis() {
        return diagnosis;
    }
    
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public String getTreatment() {
        return treatment;
    }
    
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    
    public String getObservations() {
        return observations;
    }
    
    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
