package com.vitameet.api.usermanagement.interfaces.rest.dto;

public record PatientProfileRequest(
        String name,
        String email,
        Integer age,
        String phone,
        String address,
        String diagnosis,
        String treatment,
        String date) {
}
