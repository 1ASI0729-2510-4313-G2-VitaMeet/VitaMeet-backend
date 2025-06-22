package upc.edu.pe.vitameet.appointment.domain.model.events;

import upc.edu.pe.vitameet.appointment.domain.model.valueobjects.AppointmentId;

public record AppointmentCreatedEvent(AppointmentId appointmentId) {
    public Object getAppointmentId() {
    return appointmentId;
    }
}
