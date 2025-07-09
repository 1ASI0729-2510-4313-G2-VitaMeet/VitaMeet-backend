package com.vitameet.api.usermanagement.application.commands;

import com.vitameet.api.shared.domain.Command;

public record UpdatePatientCommand(
                Long patientId,
                String name,
                String email,
                Integer age,
                String phone,
                String address,
                String diagnosis,
                String treatment,
                String date) implements Command {
}
