package upc.edu.pe.vitameet.scheduling.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import upc.edu.pe.vitameet.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import upc.edu.pe.vitameet.scheduling.domain.model.commands.CreateScheduleCommand;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends AuditableAbstractAggregateRoot<Schedule> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private LocalDateTime availableFrom;

    @Column(nullable = false)
    private LocalDateTime availableTo;

    @Column(nullable = false)
    private boolean isAvailable;

    public Schedule(CreateScheduleCommand command) {
        this.doctorId = command.doctorId();
        this.availableFrom = command.availableFrom();
        this.availableTo = command.availableTo();
        this.isAvailable = true;
        validateTimes();
    }

    public void markUnavailable() {
        this.isAvailable = false;
    }

    private void validateTimes() {
        if (availableFrom.isAfter(availableTo)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        if (availableFrom.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Schedule must start in the future.");
        }
    }
}
