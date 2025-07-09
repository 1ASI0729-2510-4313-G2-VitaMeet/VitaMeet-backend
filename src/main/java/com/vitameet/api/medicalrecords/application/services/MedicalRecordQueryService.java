package com.vitameet.api.medicalrecords.application.services;

import com.vitameet.api.medicalrecords.application.queries.GetMedicalRecordByIdQuery;
import com.vitameet.api.medicalrecords.application.queries.GetMedicalRecordsByPatientIdQuery;
import com.vitameet.api.medicalrecords.application.queries.GetMedicalRecordsByDoctorIdQuery;
import com.vitameet.api.medicalrecords.domain.model.MedicalRecord;
import com.vitameet.api.medicalrecords.domain.repositories.MedicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MedicalRecordQueryService {
    
    private final MedicalRecordRepository medicalRecordRepository;
    
    public MedicalRecordQueryService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }
    
    public Optional<MedicalRecord> getMedicalRecordById(GetMedicalRecordByIdQuery query) {
        return medicalRecordRepository.findById(query.getMedicalRecordId());
    }
    
    public List<MedicalRecord> getMedicalRecordsByPatientId(GetMedicalRecordsByPatientIdQuery query) {
        return medicalRecordRepository.findByPatientIdOrderByRegistrationDateDesc(query.getPatientId());
    }
    
    public List<MedicalRecord> getMedicalRecordsByDoctorId(GetMedicalRecordsByDoctorIdQuery query) {
        return medicalRecordRepository.findByDoctorIdOrderByRegistrationDateDesc(query.getDoctorId());
    }
    
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }
    
    public List<MedicalRecord> getMedicalRecordsByAppointmentId(Long appointmentId) {
        return medicalRecordRepository.findByAppointmentId(appointmentId);
    }
}
