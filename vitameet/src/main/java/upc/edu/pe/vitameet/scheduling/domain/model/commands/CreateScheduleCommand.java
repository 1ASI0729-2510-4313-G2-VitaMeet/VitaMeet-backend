package upc.edu.pe.vitameet.scheduling.domain.model.commands;

import java.time.LocalDateTime;

public record CreateScheduleCommand(Long doctorId, LocalDateTime availableFrom, LocalDateTime availableTo) {
    public CreateScheduleCommand {
        if (doctorId == null || doctorId <= 0)
            throw new IllegalArgumentException("DoctorId must be a positive number");
        if (availableFrom == null || availableTo == null)
            throw new IllegalArgumentException("Time range must not be null");
    }
}
