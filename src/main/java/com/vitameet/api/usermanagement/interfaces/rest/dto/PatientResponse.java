package com.vitameet.api.usermanagement.interfaces.rest.dto;

import com.vitameet.api.usermanagement.domain.model.Patient;

public record PatientResponse(
        Long id,
        String name,
        String email,
        String role,
        Integer age,
        String phone,
        String address,
        String diagnosis,
        String treatment,
        String date) {
    public static PatientResponse from(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getRole(),
                patient.getAge(),
                patient.getPhone(),
                patient.getAddress(),
                patient.getDiagnosis(),
                patient.getTreatment(),
                patient.getDate());
    }
}
