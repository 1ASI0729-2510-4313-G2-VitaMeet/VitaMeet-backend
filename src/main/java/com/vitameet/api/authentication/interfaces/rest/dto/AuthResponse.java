package com.vitameet.api.authentication.interfaces.rest.dto;

public record AuthResponse(
        Long id,
        String name,
        String email,
        String role,

        // Doctor specific fields
        String specialty,
        String license,
        Integer experience,

        // Patient specific fields
        Integer age,
        String phone,
        String address,
        String diagnosis,
        String treatment,
        String date) {
}
