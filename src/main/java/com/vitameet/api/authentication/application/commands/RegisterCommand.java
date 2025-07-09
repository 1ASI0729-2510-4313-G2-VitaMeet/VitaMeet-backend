package com.vitameet.api.authentication.application.commands;

public record RegisterCommand(
        String name,
        String email,
        String password,
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
