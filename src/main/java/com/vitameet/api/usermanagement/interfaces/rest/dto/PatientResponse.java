package com.vitameet.api.usermanagement.interfaces.rest.dto;

import com.vitameet.api.usermanagement.domain.model.Patient;
import java.time.LocalDate;

public record PatientResponse(
        Long id,
        String dni,
        String fullName,
        String email,
        LocalDate birthDate,
        Patient.Gender gender,
        int age) {
    public static PatientResponse from(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getDni(),
                patient.getFullName(),
                patient.getEmail(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAge());
    }
}
