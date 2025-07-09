package com.vitameet.api.medicalstaff.application.commands;

import com.vitameet.api.shared.domain.Command;

public record UpdateDoctorCommand(
    Long doctorId,
    String specialty,
    String officeLocation
) implements Command {}
