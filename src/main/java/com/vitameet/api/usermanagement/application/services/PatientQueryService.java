package com.vitameet.api.usermanagement.application.services;

import com.vitameet.api.usermanagement.application.queries.GetPatientByIdQuery;
import com.vitameet.api.usermanagement.domain.model.Patient;
import com.vitameet.api.usermanagement.domain.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PatientQueryService {

    private final PatientRepository patientRepository;

    public PatientQueryService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Optional<Patient> handle(GetPatientByIdQuery query) {
        return patientRepository.findById(query.patientId());
    }
}
