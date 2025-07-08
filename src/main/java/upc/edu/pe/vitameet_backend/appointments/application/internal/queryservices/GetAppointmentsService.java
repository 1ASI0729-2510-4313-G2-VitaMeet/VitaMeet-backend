package upc.edu.pe.vitameet_backend.appointments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet_backend.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

@Service
public class GetAppointmentsService {

    private final AppointmentRepository repository;

    public GetAppointmentsService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public java.util.List<upc.edu.pe.vitameet_backend.appointments.domain.model.aggregates.Appointment> getByPatientId(Long patientId) {
        return repository.findAllByPatientId(patientId);
    }
}
