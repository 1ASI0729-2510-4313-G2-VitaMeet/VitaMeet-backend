package upc.edu.pe.vitameet_backend.accounts.domain.services;

import upc.edu.pe.vitameet_backend.accounts.domain.model.aggregates.User;

public interface AuthenticationService {
    boolean authenticate(String email, String password);
    User getUserByEmail(String email);
}
