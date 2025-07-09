package com.vitameet.api.usermanagement.interfaces.rest.dto;

public record CreatePatientRequest(
                String name,
                String email,
                String password,
                Integer age,
                String phone,
                String address,
                String diagnosis,
                String treatment,
                String date) {
}
