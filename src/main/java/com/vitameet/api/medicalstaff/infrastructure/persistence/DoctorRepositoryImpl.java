package com.vitameet.api.medicalstaff.infrastructure.persistence;

import com.vitameet.api.medicalstaff.domain.model.Doctor;
import com.vitameet.api.medicalstaff.domain.repositories.DoctorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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
    public Optional<Doctor> findByMedicalLicense(String medicalLicense) {
        return jpaDoctorRepository.findByMedicalLicense(medicalLicense);
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
    public boolean existsByMedicalLicense(String medicalLicense) {
        return jpaDoctorRepository.existsByMedicalLicense(medicalLicense);
    }
}
