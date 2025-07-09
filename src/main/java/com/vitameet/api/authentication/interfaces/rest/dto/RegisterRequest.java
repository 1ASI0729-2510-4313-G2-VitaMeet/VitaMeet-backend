package com.vitameet.api.authentication.interfaces.rest.dto;

public record RegisterRequest(
        String name,
        String email,
        String password,
        String role, // "Médico" or "Paciente"

        // Doctor specific fields (optional)
        String specialty,
        String license,
        Integer experience,

        // Patient specific fields (optional)
        Integer age,
        String phone,
        String address,
        String diagnosis,
        String treatment,
        String date) {
}
