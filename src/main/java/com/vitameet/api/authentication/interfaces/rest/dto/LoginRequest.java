package com.vitameet.api.authentication.interfaces.rest.dto;

public record LoginRequest(
        String email,
        String password) {
}
