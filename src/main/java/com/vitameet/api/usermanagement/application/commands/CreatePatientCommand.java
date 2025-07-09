package com.vitameet.api.usermanagement.application.commands;

import com.vitameet.api.shared.domain.Command;

public record CreatePatientCommand(
                String name,
                String email,
                String password,
                Integer age,
                String phone,
                String address,
                String diagnosis,
                String treatment,
                String date) implements Command {
}
