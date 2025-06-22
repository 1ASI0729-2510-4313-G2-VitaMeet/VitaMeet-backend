package upc.edu.pe.vitameet.appointment.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import upc.edu.pe.vitameet.appointment.domain.model.valueobjects.AppointmentStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
