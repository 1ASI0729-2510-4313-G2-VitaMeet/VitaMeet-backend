package com.vitameet.api.authentication.application.commands;

public record LoginCommand(
        String email,
        String password) {
}
