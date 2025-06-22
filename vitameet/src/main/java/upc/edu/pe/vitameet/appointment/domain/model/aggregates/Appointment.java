package upc.edu.pe.vitameet.appointment.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import upc.edu.pe.vitameet.appointment.domain.model.commands.CreateAppointmentCommand;
import upc.edu.pe.vitameet.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    @Column(nullable = false)
    private String status;

    public Appointment(CreateAppointmentCommand command) {
        this.patientId = command.patientId();
        this.doctorId = command.doctorId();
        this.scheduledAt = command.scheduledAt();
        this.status = "CREATED";
    }

    public void cancel() {
        if (!this.status.equals("CANCELLED")) {
            this.status = "CANCELLED";
        }
    }

    public boolean isUpcoming() {
        return this.scheduledAt.isAfter(LocalDateTime.now()) && this.status.equals("CREATED");
    }
}
