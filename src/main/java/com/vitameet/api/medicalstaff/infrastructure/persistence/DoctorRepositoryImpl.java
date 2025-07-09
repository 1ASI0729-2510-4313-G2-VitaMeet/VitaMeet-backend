package com.vitameet.api.medicalstaff.infrastructure.persistence;

import com.vitameet.api.medicalstaff.domain.model.Doctor;
import com.vitameet.api.medicalstaff.domain.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorRepositoryImpl implements DoctorRepository {

    private final JpaDoctorRepository jpaDoctorRepository;

    public DoctorRepositoryImpl(JpaDoctorRepository jpaDoctorRepository) {
        this.jpaDoctorRepository = jpaDoctorRepository;
    }

    @Override
    public List<Doctor> findAll() {
        return jpaDoctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return jpaDoctorRepository.findById(id);
    }

    @Override
    public Optional<Doctor> findByEmail(String email) {
        return jpaDoctorRepository.findByEmail(email);
    }

    @Override
    public Optional<Doctor> findByLicense(String license) {
        return jpaDoctorRepository.findByLicense(license);
    }

    @Override
    public List<Doctor> findBySpecialty(String specialty) {
        return jpaDoctorRepository.findBySpecialty(specialty);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return jpaDoctorRepository.save(doctor);
    }

    @Override
    public void delete(Doctor doctor) {
        jpaDoctorRepository.delete(doctor);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaDoctorRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaDoctorRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByLicense(String license) {
        return jpaDoctorRepository.existsByLicense(license);
    }
}
