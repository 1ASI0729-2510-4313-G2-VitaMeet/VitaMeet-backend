package com.vitameet.api.medicalstaff.interfaces.rest.dto;

import com.vitameet.api.medicalstaff.domain.model.Doctor;

public record DoctorResponse(
        Long id,
        String name,
        String email,
        String role,
        String specialty,
        String license,
        Integer experience) {
    public static DoctorResponse from(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getRole(),
                doctor.getSpecialty(),
                doctor.getLicense(),
                doctor.getExperience());
    }
}
