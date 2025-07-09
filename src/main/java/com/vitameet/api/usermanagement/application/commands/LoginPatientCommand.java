package com.vitameet.api.usermanagement.application.commands;

import com.vitameet.api.shared.domain.Command;

public record LoginPatientCommand(
        String email,
        String password) implements Command {
}
