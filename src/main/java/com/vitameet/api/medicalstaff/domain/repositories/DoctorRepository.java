package com.vitameet.api.medicalstaff.domain.repositories;

import com.vitameet.api.medicalstaff.domain.model.Doctor;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository {
    List<Doctor> findAll();
    Optional<Doctor> findById(Long id);
    Optional<Doctor> findByMedicalLicense(String medicalLicense);
    List<Doctor> findBySpecialty(String specialty);
    Doctor save(Doctor doctor);
    void delete(Doctor doctor);
    boolean existsById(Long id);
    boolean existsByMedicalLicense(String medicalLicense);
}
