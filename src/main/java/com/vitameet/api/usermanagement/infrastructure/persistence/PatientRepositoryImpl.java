package com.vitameet.api.usermanagement.infrastructure.persistence;

import com.vitameet.api.usermanagement.domain.model.Patient;
import com.vitameet.api.usermanagement.domain.repositories.PatientRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private final JpaPatientRepository jpaPatientRepository;

    public PatientRepositoryImpl(JpaPatientRepository jpaPatientRepository) {
        this.jpaPatientRepository = jpaPatientRepository;
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return jpaPatientRepository.findById(id);
    }

    @Override
    public Optional<Patient> findByDni(String dni) {
        return jpaPatientRepository.findByDni(dni);
    }

    @Override
    public Optional<Patient> findByEmail(String email) {
        return jpaPatientRepository.findByEmail(email);
    }

    @Override
    public Optional<Patient> findByEmailAndPassword(String email, String password) {
        return jpaPatientRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Patient save(Patient patient) {
        return jpaPatientRepository.save(patient);
    }

    @Override
    public void delete(Patient patient) {
        jpaPatientRepository.delete(patient);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaPatientRepository.existsById(id);
    }

    @Override
    public boolean existsByDni(String dni) {
        return jpaPatientRepository.existsByDni(dni);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaPatientRepository.existsByEmail(email);
    }
}
