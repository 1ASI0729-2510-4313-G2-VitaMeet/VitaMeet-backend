package com.vitameet.api.usermanagement.application.commands;

import com.vitameet.api.shared.domain.Command;
import com.vitameet.api.usermanagement.domain.model.Patient;
import java.time.LocalDate;

public record CreatePatientCommand(
        String dni,
        String fullName,
        String email,
        String password,
        LocalDate birthDate,
        Patient.Gender gender) implements Command {
}
