package upc.edu.pe.vitameet.evaluation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.vitameet.evaluation.domain.model.aggregates.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
