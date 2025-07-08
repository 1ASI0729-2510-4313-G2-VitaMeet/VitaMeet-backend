package upc.edu.pe.vitameet_backend.appointments.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.edu.pe.vitameet_backend.appointments.domain.model.aggregates.Appointment;
import upc.edu.pe.vitameet_backend.appointments.domain.model.commands.BookAppointmentCommand;
import upc.edu.pe.vitameet_backend.appointments.domain.model.valueobjects.AppointmentTime;
import upc.edu.pe.vitameet_backend.appointments.domain.model.valueobjects.AppointmentStatus;
import upc.edu.pe.vitameet_backend.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

@Service
public class BookAppointmentService {

    private final AppointmentRepository repository;

    public BookAppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Appointment handle(BookAppointmentCommand command) {
        Appointment appointment = new Appointment(
                new AppointmentTime(command.appointmentTime()),
                new AppointmentStatus("SCHEDULED"),
                command.patientId()
        );
        return repository.save(appointment);
    }
}
