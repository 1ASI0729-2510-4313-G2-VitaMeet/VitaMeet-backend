package upc.edu.pe.vitameet_backend.appointments.interfaces.rest.resource;

import java.time.LocalDateTime;

public record BookAppointmentResource(LocalDateTime appointmentTime, Long patientId) {}
