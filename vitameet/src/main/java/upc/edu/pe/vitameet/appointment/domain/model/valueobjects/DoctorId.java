package upc.edu.pe.vitameet.appointment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record DoctorId(Long value) {
    public DoctorId {
        if (value == null || value <= 0)
            throw new IllegalArgumentException("DoctorId must be a positive number");
    }

    public DoctorId() {
        this(0L);
    }
}
