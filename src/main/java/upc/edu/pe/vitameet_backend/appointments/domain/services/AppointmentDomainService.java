package upc.edu.pe.vitameet_backend.appointments.domain.services;

import upc.edu.pe.vitameet_backend.appointments.domain.model.aggregates.Appointment;

public class AppointmentDomainService {
    public boolean isEligible(Appointment appointment) {
        return appointment.getAppointmentTime().getDateTime().isAfter(java.time.LocalDateTime.now());
    }
}
