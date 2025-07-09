package com.vitameet.api.medicalrecords.application.services;

import com.vitameet.api.medicalrecords.application.commands.CreateMedicalRecordCommand;
import com.vitameet.api.medicalrecords.application.commands.UpdateMedicalRecordCommand;
import com.vitameet.api.medicalrecords.domain.model.MedicalRecord;
import com.vitameet.api.medicalrecords.domain.repositories.MedicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicalRecordCommandService {
    
    private final MedicalRecordRepository medicalRecordRepository;
    
    public MedicalRecordCommandService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }
    
    public MedicalRecord createMedicalRecord(CreateMedicalRecordCommand command) {
        var medicalRecord = new MedicalRecord(
            command.getPatientId(),
            command.getDoctorId(),
            command.getAppointmentId(),
            command.getDiagnosis(),
            command.getTreatment(),
            command.getObservations()
        );
        
        return medicalRecordRepository.save(medicalRecord);
    }
    
    public MedicalRecord updateMedicalRecord(UpdateMedicalRecordCommand command) {
        var medicalRecord = medicalRecordRepository.findById(command.getMedicalRecordId())
            .orElseThrow(() -> new IllegalArgumentException("Historial médico no encontrado"));
        
        medicalRecord.updateMedicalInformation(
            command.getDiagnosis(),
            command.getTreatment(),
            command.getObservations()
        );
        
        return medicalRecordRepository.save(medicalRecord);
    }
    
    public void deleteMedicalRecord(Long medicalRecordId) {
        if (!medicalRecordRepository.existsById(medicalRecordId)) {
            throw new IllegalArgumentException("Historial médico no encontrado");
        }
        medicalRecordRepository.deleteById(medicalRecordId);
    }
}
