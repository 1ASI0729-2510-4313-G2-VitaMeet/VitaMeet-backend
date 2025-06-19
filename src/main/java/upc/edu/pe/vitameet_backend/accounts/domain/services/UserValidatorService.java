package upc.edu.pe.vitameet_backend.accounts.domain.services;

import upc.edu.pe.vitameet_backend.accounts.domain.model.aggregates.User;

public class UserValidatorService {
    public boolean isValid(User user) {
        return user.getEmail() != null && user.getPassword() != null;
    }
}
