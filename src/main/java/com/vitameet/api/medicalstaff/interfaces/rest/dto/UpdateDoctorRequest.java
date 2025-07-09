package com.vitameet.api.medicalstaff.interfaces.rest.dto;

public record UpdateDoctorRequest(
        String name,
        String email,
        String specialty,
        Integer experience) {
}
