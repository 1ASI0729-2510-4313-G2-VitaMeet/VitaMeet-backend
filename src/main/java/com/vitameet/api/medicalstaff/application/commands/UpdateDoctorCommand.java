package com.vitameet.api.medicalstaff.application.commands;

import com.vitameet.api.shared.domain.Command;

public record UpdateDoctorCommand(
        Long doctorId,
        String name,
        String email,
        String specialty,
        Integer experience) implements Command {
}
