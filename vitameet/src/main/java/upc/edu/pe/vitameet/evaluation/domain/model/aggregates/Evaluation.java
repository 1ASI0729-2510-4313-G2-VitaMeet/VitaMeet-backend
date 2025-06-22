package upc.edu.pe.vitameet.evaluation.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import upc.edu.pe.vitameet.evaluation.domain.model.commands.CreateEvaluationCommand;
import upc.edu.pe.vitameet.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
@NoArgsConstructor
public class Evaluation extends AuditableAbstractAggregateRoot<Evaluation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long appointmentId;

    @Column(nullable = false)
    private int rating;

    @Column(length = 500)
    private String comment;

    public Evaluation(CreateEvaluationCommand command) {
        if (command.rating() < 1 || command.rating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        if (command.comment() != null && command.comment().length() > 500) {
            throw new IllegalArgumentException("Comment must be 500 characters or less");
        }
        this.appointmentId = command.appointmentId();
        this.rating = command.rating();
        this.comment = command.comment();
    }
}
