package upc.edu.pe.vitameet_backend.appointments.domain.model.aggregates;

import jakarta.persistence.*;
import upc.edu.pe.vitameet_backend.appointments.domain.model.valueobjects.AppointmentStatus;
import upc.edu.pe.vitameet_backend.appointments.domain.model.valueobjects.AppointmentTime;
import upc.edu.pe.vitameet_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "appointments")
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    @Embedded
    private AppointmentTime appointmentTime;

    @Embedded
    private AppointmentStatus status;

    private Long patientId;
    @Id
    private Long id;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
