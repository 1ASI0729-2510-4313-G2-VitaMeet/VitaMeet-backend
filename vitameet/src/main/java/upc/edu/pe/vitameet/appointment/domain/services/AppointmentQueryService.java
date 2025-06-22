package upc.edu.pe.vitameet.appointment.domain.services;

import upc.edu.pe.vitameet.appointment.domain.model.aggregates.Appointment;
import upc.edu.pe.vitameet.appointment.domain.model.queries.GetAllAppointmentsQuery;
import upc.edu.pe.vitameet.appointment.domain.model.queries.GetAppointmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    Optional<Appointment> handle(GetAppointmentByIdQuery query);
    List<Appointment> handle(GetAllAppointmentsQuery query);
}
