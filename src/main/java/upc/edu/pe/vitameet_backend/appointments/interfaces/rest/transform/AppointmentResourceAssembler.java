package upc.edu.pe.vitameet_backend.appointments.interfaces.rest.transform;

import upc.edu.pe.vitameet_backend.appointments.domain.model.commands.BookAppointmentCommand;
import upc.edu.pe.vitameet_backend.appointments.interfaces.rest.resource.BookAppointmentResource;

public class AppointmentResourceAssembler {
    public static BookAppointmentCommand toCommand(BookAppointmentResource resource) {
        return new BookAppointmentCommand(resource.appointmentTime(), resource.patientId());
    }
}
