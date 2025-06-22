package upc.edu.pe.vitameet.appointment.domain.model.commands;

import java.time.LocalDateTime;

public record CreateAppointmentCommand(Long doctorId, Long patientId, LocalDateTime scheduledAt) {
    public CreateAppointmentCommand {
        if (doctorId == null || doctorId <= 0)
            throw new IllegalArgumentException("DoctorId must be a positive number");
        if (patientId == null || patientId <= 0)
            throw new IllegalArgumentException("PatientId must be a positive number");
        if (scheduledAt == null || scheduledAt.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Appointment date must be in the future");
    }
}
