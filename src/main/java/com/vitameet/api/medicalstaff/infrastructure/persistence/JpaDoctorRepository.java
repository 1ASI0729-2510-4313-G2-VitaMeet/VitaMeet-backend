package com.vitameet.api.medicalstaff.infrastructure.persistence;

import com.vitameet.api.medicalstaff.domain.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaDoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);

    Optional<Doctor> findByLicense(String license);

    List<Doctor> findBySpecialty(String specialty);

    boolean existsByEmail(String email);

    boolean existsByLicense(String license);
}
