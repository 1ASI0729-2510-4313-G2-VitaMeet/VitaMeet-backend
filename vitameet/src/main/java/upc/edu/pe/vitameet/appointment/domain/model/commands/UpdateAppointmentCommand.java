package upc.edu.pe.vitameet.appointment.domain.model.commands;

import java.time.LocalDateTime;

public record UpdateAppointmentCommand(Long appointmentId, LocalDateTime newDate) {
    public UpdateAppointmentCommand {
        if (appointmentId == null || appointmentId <= 0)
            throw new IllegalArgumentException("AppointmentId must be a positive number");
        if (newDate == null || newDate.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("New appointment date must be in the future");
    }
}
