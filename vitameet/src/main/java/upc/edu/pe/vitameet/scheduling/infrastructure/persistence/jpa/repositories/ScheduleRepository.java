package upc.edu.pe.vitameet.scheduling.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.vitameet.scheduling.domain.model.aggregates.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
