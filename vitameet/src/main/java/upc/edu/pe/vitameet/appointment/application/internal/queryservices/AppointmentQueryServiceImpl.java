package upc.edu.pe.vitameet.appointment.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet.appointment.domain.model.aggregates.Appointment;
import upc.edu.pe.vitameet.appointment.domain.model.queries.GetAllAppointmentsQuery;
import upc.edu.pe.vitameet.appointment.domain.model.queries.GetAppointmentByIdQuery;
import upc.edu.pe.vitameet.appointment.domain.services.AppointmentQueryService;
import upc.edu.pe.vitameet.appointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.appointmentId());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return appointmentRepository.findAll();
    }
}
