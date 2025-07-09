package com.vitameet.api.usermanagement.interfaces.rest.dto;

public record LoginRequest(
        String email,
        String password) {
}
