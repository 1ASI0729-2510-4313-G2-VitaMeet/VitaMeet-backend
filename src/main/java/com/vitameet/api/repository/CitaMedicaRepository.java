package com.vitameet.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vitameet.api.model.CitaMedica;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {
    
}
