package com.vitameet.api.medicalrecords.application.commands;

import com.vitameet.api.shared.domain.Command;

public class CreateMedicalRecordCommand implements Command {
    private final Long patientId;
    private final Long doctorId;
    private final Long appointmentId;
    private final String diagnosis;
    private final String treatment;
    private final String observations;
    
    public CreateMedicalRecordCommand(Long patientId, Long doctorId, Long appointmentId,
                                     String diagnosis, String treatment, String observations) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.observations = observations;
    }
    
    public Long getPatientId() {
        return patientId;
    }
    
    public Long getDoctorId() {
        return doctorId;
    }
    
    public Long getAppointmentId() {
        return appointmentId;
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
