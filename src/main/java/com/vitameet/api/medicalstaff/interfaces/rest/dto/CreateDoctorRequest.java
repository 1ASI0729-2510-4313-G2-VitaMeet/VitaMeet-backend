package com.vitameet.api.medicalstaff.interfaces.rest.dto;

public record CreateDoctorRequest(
        String name,
        String email,
        String password,
        String specialty,
        String license,
        Integer experience) {
}
