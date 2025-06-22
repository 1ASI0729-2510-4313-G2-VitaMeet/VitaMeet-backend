package upc.edu.pe.vitameet.appointment.application.internal.eventhandlers;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet.appointment.domain.model.events.AppointmentCreatedEvent;
import upc.edu.pe.vitameet.appointment.domain.model.events.AppointmentCancelledEvent;

@Service
public class AppointmentEventHandler {

    @EventListener(AppointmentCreatedEvent.class)
    public void on(AppointmentCreatedEvent event) {
        var appointmentId = event.getAppointmentId();
        // lógica relacionada con la creación de citas (por ejemplo: enviar notificación, actualizar log de auditoría)
    }

    @EventListener(AppointmentCancelledEvent.class)
    public void on(AppointmentCancelledEvent event) {
        var appointmentId = event.getAppointmentId();
        // lógica relacionada con la cancelación de citas (por ejemplo: liberar el horario, notificar al paciente)
    }
}
