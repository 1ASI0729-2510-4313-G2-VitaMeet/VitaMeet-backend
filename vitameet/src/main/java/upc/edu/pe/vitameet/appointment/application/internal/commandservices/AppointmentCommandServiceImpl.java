package upc.edu.pe.vitameet.appointment.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet.appointment.domain.model.aggregates.Appointment;
import upc.edu.pe.vitameet.appointment.domain.model.commands.CreateAppointmentCommand;
import upc.edu.pe.vitameet.appointment.domain.model.commands.CancelAppointmentCommand;
import upc.edu.pe.vitameet.appointment.domain.services.AppointmentCommandService;
import upc.edu.pe.vitameet.appointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentRepository repository;

    public AppointmentCommandServiceImpl(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Appointment handle(CreateAppointmentCommand command) {
        Appointment appointment = new Appointment(command);
        return repository.save(appointment);
    }

    @Override
    public void handle(CancelAppointmentCommand command) {
        repository.findById(command.appointmentId()).ifPresent(Appointment::cancel);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
