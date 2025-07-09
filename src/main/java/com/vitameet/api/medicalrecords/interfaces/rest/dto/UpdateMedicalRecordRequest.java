package com.vitameet.api.medicalrecords.interfaces.rest.dto;

public class UpdateMedicalRecordRequest {
    
    private String diagnosis;
    private String treatment;
    private String observations;
    
    // Constructors
    public UpdateMedicalRecordRequest() {
    }
    
    public UpdateMedicalRecordRequest(String diagnosis, String treatment, String observations) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.observations = observations;
    }
    
    // Getters and Setters
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
}
