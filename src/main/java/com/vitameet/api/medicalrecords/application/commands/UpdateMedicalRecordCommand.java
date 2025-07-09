package com.vitameet.api.medicalrecords.application.commands;

import com.vitameet.api.shared.domain.Command;

public class UpdateMedicalRecordCommand implements Command {
    private final Long medicalRecordId;
    private final String diagnosis;
    private final String treatment;
    private final String observations;
    
    public UpdateMedicalRecordCommand(Long medicalRecordId, String diagnosis, 
                                     String treatment, String observations) {
        this.medicalRecordId = medicalRecordId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.observations = observations;
    }
    
    public Long getMedicalRecordId() {
        return medicalRecordId;
    }
    
    public String getDiagnosis() {
        return diagnosis;
    }
    
    public String getTreatment() {
        return treatment;
    }
    
    public String getObservations() {
        return observations;
    }
}
