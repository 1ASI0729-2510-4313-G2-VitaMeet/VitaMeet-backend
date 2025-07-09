package com.vitameet.api.medicalstaff.application.commands;

import com.vitameet.api.shared.domain.Command;

public record CreateDoctorCommand(
    String specialty,
    String medicalLicense,
    String officeLocation
) implements Command {}
