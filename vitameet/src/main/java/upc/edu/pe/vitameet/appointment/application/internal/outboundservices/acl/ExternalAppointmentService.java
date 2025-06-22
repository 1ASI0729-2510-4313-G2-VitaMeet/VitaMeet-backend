package upc.edu.pe.vitameet.appointment.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;

@Service
public class ExternalAppointmentService {

    // Este servicio está diseñado para integraciones futuras con otros bounded contexts o sistemas externos
    // Ejemplo: enviar notificación a pacientes o médicos, integración con calendario, etc.

    public void notifyAppointmentCreated(Long appointmentId) {
        // lógica futura para manejar la notificación o integración externa cuando se crea una cita
    }

    public void notifyAppointmentCancelled(Long appointmentId) {
        // lógica futura para manejar la notificación o integración externa cuando se cancela una cita
    }
}
