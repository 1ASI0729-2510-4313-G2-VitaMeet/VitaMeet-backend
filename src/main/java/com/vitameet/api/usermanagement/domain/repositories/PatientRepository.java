package com.vitameet.api.usermanagement.domain.repositories;

import com.vitameet.api.usermanagement.domain.model.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    List<Patient> findAll();

    Optional<Patient> findById(Long id);

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByEmailAndPassword(String email, String password);

    Patient save(Patient patient);

    void delete(Patient patient);

    boolean existsById(Long id);

    boolean existsByEmail(String email);
}
