package upc.edu.pe.vitameet.appointment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public record AppointmentDate(LocalDateTime value) {
    public AppointmentDate {
        if (value == null || value.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Appointment date must be in the future");
    }

    public AppointmentDate() {
        this(LocalDateTime.now().plusDays(1));
    }
}
