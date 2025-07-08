package upc.edu.pe.vitameet_backend.appointments.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.edu.pe.vitameet_backend.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

@Service
public class CancelAppointmentService {

    private final AppointmentRepository repository;

    public CancelAppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void handle(Long id) {
        var appointment = repository.findById(id).orElseThrow();
        appointment.cancel();
        repository.save(appointment);
    }
}
