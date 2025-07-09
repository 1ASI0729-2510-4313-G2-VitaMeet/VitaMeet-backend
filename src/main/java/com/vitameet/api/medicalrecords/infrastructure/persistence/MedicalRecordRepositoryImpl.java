package com.vitameet.api.medicalrecords.infrastructure.persistence;

import com.vitameet.api.medicalrecords.domain.model.MedicalRecord;
import com.vitameet.api.medicalrecords.domain.repositories.MedicalRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {
    
    private final JpaMedicalRecordRepository jpaMedicalRecordRepository;
    
    public MedicalRecordRepositoryImpl(JpaMedicalRecordRepository jpaMedicalRecordRepository) {
        this.jpaMedicalRecordRepository = jpaMedicalRecordRepository;
    }
    
    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        return jpaMedicalRecordRepository.save(medicalRecord);
    }
    
    @Override
    public Optional<MedicalRecord> findById(Long id) {
        return jpaMedicalRecordRepository.findById(id);
    }
    
    @Override
    public List<MedicalRecord> findAll() {
        return jpaMedicalRecordRepository.findAll();
    }
    
    @Override
    public void delete(MedicalRecord medicalRecord) {
        jpaMedicalRecordRepository.delete(medicalRecord);
    }
    
    @Override
    public void deleteById(Long id) {
        jpaMedicalRecordRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaMedicalRecordRepository.existsById(id);
    }
    
    @Override
    public List<MedicalRecord> findByPatientId(Long patientId) {
        return jpaMedicalRecordRepository.findByPatientId(patientId);
    }
    
    @Override
    public List<MedicalRecord> findByDoctorId(Long doctorId) {
        return jpaMedicalRecordRepository.findByDoctorId(doctorId);
    }
    
    @Override
    public List<MedicalRecord> findByAppointmentId(Long appointmentId) {
        return jpaMedicalRecordRepository.findByAppointmentId(appointmentId);
    }
    
    @Override
    public List<MedicalRecord> findByPatientIdOrderByRegistrationDateDesc(Long patientId) {
        return jpaMedicalRecordRepository.findByPatientIdOrderByRegistrationDateDesc(patientId);
    }
    
    @Override
    public List<MedicalRecord> findByDoctorIdOrderByRegistrationDateDesc(Long doctorId) {
        return jpaMedicalRecordRepository.findByDoctorIdOrderByRegistrationDateDesc(doctorId);
    }
}
