package upc.edu.pe.vitameet.appointment.domain.model.events;

import upc.edu.pe.vitameet.appointment.domain.model.valueobjects.AppointmentId;

public record AppointmentCancelledEvent(AppointmentId appointmentId) {
    public Object getAppointmentId() {
        return appointmentId;
    }
}
