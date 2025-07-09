package com.vitameet.api.medicalrecords.domain.model;

import com.vitameet.api.shared.domain.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historiales")
public class MedicalRecord extends BaseEntity {
    
    @Column(name = "paciente_id", nullable = false)
    private Long patientId;
    
    @Column(name = "medico_id", nullable = false)
    private Long doctorId;
    
    @Column(name = "cita_id")
    private Long appointmentId;
    
    @Column(name = "diagnostico", columnDefinition = "TEXT")
    private String diagnosis;
    
    @Column(name = "tratamiento", columnDefinition = "TEXT")
    private String treatment;
    
    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observations;
    
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime registrationDate;
    
    // Constructors
    protected MedicalRecord() {
    }
    
    public MedicalRecord(Long patientId, Long doctorId, Long appointmentId, 
                        String diagnosis, String treatment, String observations) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.observations = observations;
        this.registrationDate = LocalDateTime.now();
    }
    
    // Business methods
    public void updateMedicalInformation(String diagnosis, String treatment, String observations) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.observations = observations;
    }
    
    public boolean isFromAppointment() {
        return appointmentId != null;
    }
    
    // Getters
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
    
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
