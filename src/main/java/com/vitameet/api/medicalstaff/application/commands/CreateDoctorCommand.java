package com.vitameet.api.medicalstaff.application.commands;

import com.vitameet.api.shared.domain.Command;

public record CreateDoctorCommand(
        String name,
        String email,
        String password,
        String specialty,
        String license,
        Integer experience) implements Command {
}
