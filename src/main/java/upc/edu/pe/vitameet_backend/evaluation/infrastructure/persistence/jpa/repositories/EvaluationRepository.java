package upc.edu.pe.vitameet_backend.evaluation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.vitameet_backend.evaluation.domain.model.aggregates.Evaluation;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findAllByDoctorId(Long doctorId);
}
