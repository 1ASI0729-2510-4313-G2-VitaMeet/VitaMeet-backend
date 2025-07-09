package com.vitameet.api.usermanagement.interfaces.rest.dto;

import com.vitameet.api.usermanagement.domain.model.Patient;
import java.time.LocalDate;

public record CreatePatientRequest(
        String dni,
        String fullName,
        String email,
        String password,
        LocalDate birthDate,
        Patient.Gender gender) {
}
