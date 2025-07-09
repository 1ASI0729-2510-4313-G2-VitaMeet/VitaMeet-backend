package com.vitameet.api.authentication.application.services;

import com.vitameet.api.authentication.application.commands.LoginCommand;
import com.vitameet.api.authentication.application.commands.RegisterCommand;
import com.vitameet.api.medicalstaff.domain.model.Doctor;
import com.vitameet.api.medicalstaff.domain.repositories.DoctorRepository;
import com.vitameet.api.usermanagement.domain.model.Patient;
import com.vitameet.api.usermanagement.domain.model.User;
import com.vitameet.api.usermanagement.domain.repositories.PatientRepository;
import com.vitameet.api.usermanagement.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AuthenticationService(UserRepository userRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public User register(RegisterCommand command) {
        // Create base user
        User user = new User(command.name(), command.email(), command.password(), command.role());
        User savedUser = userRepository.save(user);

        // Create specific role entity
        if ("Médico".equals(command.role())) {
            Doctor doctor = new Doctor(
                    command.name(),
                    command.email(),
                    command.password(),
                    command.specialty(),
                    command.license(),
                    command.experience());
            doctorRepository.save(doctor);
        } else if ("Paciente".equals(command.role())) {
            Patient patient = new Patient(
                    command.name(),
                    command.email(),
                    command.password(),
                    command.age(),
                    command.phone(),
                    command.address(),
                    command.diagnosis(),
                    command.treatment(),
                    command.date());
            patientRepository.save(patient);
        }

        return savedUser;
    }

    public Optional<Object> login(LoginCommand command) {
        // Try to find user in User table first
        Optional<User> user = userRepository.findByEmail(command.email());
        if (user.isEmpty()) {
            return Optional.empty();
        }

        User foundUser = user.get();

        // Verify password (in production, use proper password hashing)
        if (!foundUser.getPassword().equals(command.password())) {
            return Optional.empty();
        }

        // Return the specific role entity with all details
        if ("Médico".equals(foundUser.getRole())) {
            return doctorRepository.findByEmail(command.email()).map(Object.class::cast);
        } else if ("Paciente".equals(foundUser.getRole())) {
            return patientRepository.findByEmail(command.email()).map(Object.class::cast);
        }

        return Optional.of(foundUser);
    }
}
