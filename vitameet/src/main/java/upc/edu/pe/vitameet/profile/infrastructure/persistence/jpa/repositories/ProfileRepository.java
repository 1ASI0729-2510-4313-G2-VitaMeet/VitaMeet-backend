package upc.edu.pe.vitameet.profile.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.vitameet.profile.domain.model.aggregates.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
