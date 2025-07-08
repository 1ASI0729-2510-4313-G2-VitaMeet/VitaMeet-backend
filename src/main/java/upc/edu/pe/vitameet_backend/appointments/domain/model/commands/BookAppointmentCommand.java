package upc.edu.pe.vitameet_backend.appointments.domain.model.commands;

import java.time.LocalDateTime;

public record BookAppointmentCommand(LocalDateTime appointmentTime, Long patientId) {}
