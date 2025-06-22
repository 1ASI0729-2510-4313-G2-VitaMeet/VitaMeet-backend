package upc.edu.pe.vitameet.appointment.interfaces.rest.transform;

import upc.edu.pe.vitameet.appointment.domain.model.aggregates.Appointment;
import upc.edu.pe.vitameet.appointment.interfaces.rest.resource.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {

    public static AppointmentResource toResource(Appointment entity) {
        return new AppointmentResource(
                entity.getId(),
                entity.getScheduledAt().toString(),
                entity.getStatus()
        );
    }
}