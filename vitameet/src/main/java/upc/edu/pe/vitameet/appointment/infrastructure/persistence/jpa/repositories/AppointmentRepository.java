package upc.edu.pe.vitameet.appointment.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.vitameet.appointment.domain.model.aggregates.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
