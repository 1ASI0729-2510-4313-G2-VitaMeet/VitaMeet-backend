package upc.edu.pe.vitameet_backend.evaluation.domain.model.aggregates;

import jakarta.persistence.*;
import upc.edu.pe.vitameet_backend.evaluation.domain.model.valueobjects.Rating;
import upc.edu.pe.vitameet_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "evaluations")
public class Evaluation extends AuditableAbstractAggregateRoot<Evaluation> {

    @Embedded
    private Rating rating;

    private String comment;

    private Long doctorId;

    private Long patientId;
    @Id
    private Long id;

    protected Evaluation() {}

    public Evaluation(Rating rating, String comment, Long doctorId, Long patientId) {
        this.rating = rating;
        this.comment = comment;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Rating getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
