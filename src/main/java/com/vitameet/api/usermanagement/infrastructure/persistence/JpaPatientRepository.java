package com.vitameet.api.usermanagement.infrastructure.persistence;

import com.vitameet.api.usermanagement.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaPatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByDni(String dni);

    Optional<Patient> findByEmail(String email);

    @Query("SELECT p FROM Patient p WHERE p.email = :email AND p.password = :password")
    Optional<Patient> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    boolean existsByDni(String dni);

    boolean existsByEmail(String email);
}
