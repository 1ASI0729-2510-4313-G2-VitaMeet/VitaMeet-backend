package com.vitameet.api.medicalrecords.infrastructure.persistence;

import com.vitameet.api.medicalrecords.domain.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaMedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    
    List<MedicalRecord> findByPatientId(Long patientId);
    
    List<MedicalRecord> findByDoctorId(Long doctorId);
    
    List<MedicalRecord> findByAppointmentId(Long appointmentId);
    
    @Query("SELECT mr FROM MedicalRecord mr WHERE mr.patientId = :patientId ORDER BY mr.registrationDate DESC")
    List<MedicalRecord> findByPatientIdOrderByRegistrationDateDesc(@Param("patientId") Long patientId);
    
    @Query("SELECT mr FROM MedicalRecord mr WHERE mr.doctorId = :doctorId ORDER BY mr.registrationDate DESC")
    List<MedicalRecord> findByDoctorIdOrderByRegistrationDateDesc(@Param("doctorId") Long doctorId);
}
