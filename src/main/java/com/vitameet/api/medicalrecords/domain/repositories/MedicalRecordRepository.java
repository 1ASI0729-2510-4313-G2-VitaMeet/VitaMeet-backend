package com.vitameet.api.medicalrecords.domain.repositories;

import com.vitameet.api.medicalrecords.domain.model.MedicalRecord;
import java.util.List;
import java.util.Optional;

public interface MedicalRecordRepository {
    MedicalRecord save(MedicalRecord medicalRecord);
    Optional<MedicalRecord> findById(Long id);
    List<MedicalRecord> findAll();
    void delete(MedicalRecord medicalRecord);
    void deleteById(Long id);
    boolean existsById(Long id);
    
    // Business-specific queries
    List<MedicalRecord> findByPatientId(Long patientId);
    List<MedicalRecord> findByDoctorId(Long doctorId);
    List<MedicalRecord> findByAppointmentId(Long appointmentId);
    List<MedicalRecord> findByPatientIdOrderByRegistrationDateDesc(Long patientId);
    List<MedicalRecord> findByDoctorIdOrderByRegistrationDateDesc(Long doctorId);
}
