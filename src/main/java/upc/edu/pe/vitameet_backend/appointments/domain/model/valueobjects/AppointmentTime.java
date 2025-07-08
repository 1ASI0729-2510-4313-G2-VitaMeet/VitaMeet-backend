package upc.edu.pe.vitameet_backend.appointments.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class AppointmentTime {
    private LocalDateTime dateTime;

    protected AppointmentTime() {}

    public AppointmentTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
