package upc.edu.pe.vitameet_backend.appointments.domain.model.aggregates;

import jakarta.persistence.*;
import upc.edu.pe.vitameet_backend.appointments.domain.model.valueobjects.AppointmentStatus;
import upc.edu.pe.vitameet_backend.appointments.domain.model.valueobjects.AppointmentTime;
import upc.edu.pe.vitameet_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "appointments")
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AppointmentTime appointmentTime;

    @Embedded
    private AppointmentStatus status;

    private Long patientId;

    protected Appointment() {}

    public Appointment(AppointmentTime appointmentTime, AppointmentStatus status, Long patientId) {
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.patientId = patientId;
    }

    public AppointmentTime getAppointmentTime() {
        return appointmentTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void cancel() {
        this.status = new AppointmentStatus("CANCELLED");
    }

    public Long getId() {
        return id;
    }
}
