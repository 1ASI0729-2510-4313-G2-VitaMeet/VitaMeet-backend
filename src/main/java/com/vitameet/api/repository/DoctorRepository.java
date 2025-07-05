package com.vitameet.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vitameet.api.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
