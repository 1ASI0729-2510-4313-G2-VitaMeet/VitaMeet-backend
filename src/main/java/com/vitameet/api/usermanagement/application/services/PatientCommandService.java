package com.vitameet.api.usermanagement.application.services;

import com.vitameet.api.usermanagement.application.commands.CreatePatientCommand;
import com.vitameet.api.usermanagement.application.commands.UpdatePatientCommand;
import com.vitameet.api.usermanagement.application.commands.LoginPatientCommand;
import com.vitameet.api.usermanagement.domain.model.Patient;
import com.vitameet.api.usermanagement.domain.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PatientCommandService {

    private final PatientRepository patientRepository;

    public PatientCommandService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient handle(CreatePatientCommand command) {
        // Domain validation
        if (patientRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("Ya existe un paciente con ese email");
        }

        // Create domain entity
        Patient patient = new Patient(
                command.name(),
                command.email(),
                command.password(), // In real app, hash this
                command.age(),
                command.phone(),
                command.address(),
                command.diagnosis(),
                command.treatment(),
                command.date());

        return patientRepository.save(patient);
    }

    public Patient handle(UpdatePatientCommand command) {
        Patient patient = patientRepository.findById(command.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));

        // Check email uniqueness if changed
        if (!patient.getEmail().equals(command.email()) &&
                patientRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("Ya existe un paciente con ese email");
        }

        // Update using domain methods
        patient.updateProfile(
                command.name(),
                command.email(),
                command.age(),
                command.phone(),
                command.address(),
                command.diagnosis(),
                command.treatment(),
                command.date());

        return patientRepository.save(patient);
    }

    public Optional<Patient> handle(LoginPatientCommand command) {
        return patientRepository.findByEmailAndPassword(command.email(), command.password());
    }

    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));

        patientRepository.delete(patient);
    }
}
