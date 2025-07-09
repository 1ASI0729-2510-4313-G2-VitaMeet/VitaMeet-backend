package com.vitameet.api.medicalstaff.infrastructure.persistence;

import com.vitameet.api.medicalstaff.domain.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface JpaDoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByMedicalLicense(String medicalLicense);
    List<Doctor> findBySpecialty(String specialty);
    boolean existsByMedicalLicense(String medicalLicense);
}
