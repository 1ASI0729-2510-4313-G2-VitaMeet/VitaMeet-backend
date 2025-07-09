package com.vitameet.api.usermanagement.domain.repositories;

import com.vitameet.api.usermanagement.domain.model.Patient;
import java.util.Optional;

public interface PatientRepository {
    Optional<Patient> findById(Long id);

    Optional<Patient> findByDni(String dni);

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByEmailAndPassword(String email, String password);

    Patient save(Patient patient);

    void delete(Patient patient);

    boolean existsById(Long id);

    boolean existsByDni(String dni);

    boolean existsByEmail(String email);
}
