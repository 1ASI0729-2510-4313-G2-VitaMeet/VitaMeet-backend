package upc.edu.pe.vitameet.appointment.domain.model.queries;

public record GetAppointmentByIdQuery(Long appointmentId) {
    public GetAppointmentByIdQuery {
        if (appointmentId == null || appointmentId <= 0)
            throw new IllegalArgumentException("AppointmentId must be a positive number");
    }
}
