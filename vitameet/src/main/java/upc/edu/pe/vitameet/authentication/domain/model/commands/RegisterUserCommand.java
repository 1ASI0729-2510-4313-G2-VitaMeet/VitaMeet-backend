package upc.edu.pe.vitameet.authentication.domain.model.commands;

public record RegisterUserCommand(String username, String password, String role) { }
