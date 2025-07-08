package upc.edu.pe.vitameet_backend.api.repository;

import upc.edu.pe.vitameet_backend.api.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
