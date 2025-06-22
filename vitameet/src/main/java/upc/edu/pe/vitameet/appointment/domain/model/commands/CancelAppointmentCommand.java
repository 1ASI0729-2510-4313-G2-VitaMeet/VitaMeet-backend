package upc.edu.pe.vitameet.appointment.domain.model.commands;

public record CancelAppointmentCommand(Long appointmentId) {
    public CancelAppointmentCommand {
        if (appointmentId == null || appointmentId <= 0)
            throw new IllegalArgumentException("AppointmentId must be a positive number");
    }
}
