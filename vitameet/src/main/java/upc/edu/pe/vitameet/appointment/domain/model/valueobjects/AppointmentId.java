package upc.edu.pe.vitameet.appointment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record AppointmentId(Long value) {
    public AppointmentId {
        if (value == null || value <= 0)
            throw new IllegalArgumentException("AppointmentId must be a positive number");
    }

    public AppointmentId() {
        this(0L);
    }
}
