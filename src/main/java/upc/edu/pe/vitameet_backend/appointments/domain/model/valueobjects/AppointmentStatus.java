package upc.edu.pe.vitameet_backend.appointments.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class AppointmentStatus {
    private String status;

    protected AppointmentStatus() {}

    public AppointmentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
