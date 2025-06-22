package upc.edu.pe.vitameet.appointment.domain.services;

import upc.edu.pe.vitameet.appointment.domain.model.aggregates.Appointment;
import upc.edu.pe.vitameet.appointment.domain.model.commands.CreateAppointmentCommand;
import upc.edu.pe.vitameet.appointment.domain.model.commands.CancelAppointmentCommand;

public interface AppointmentCommandService {
    Appointment handle(CreateAppointmentCommand command);
    void handle(CancelAppointmentCommand command);

    void delete(Long id);
}
