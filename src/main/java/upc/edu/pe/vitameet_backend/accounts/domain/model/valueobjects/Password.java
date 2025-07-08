package upc.edu.pe.vitameet_backend.accounts.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Password {

    private String value;

    protected Password() {}

    public Password(String value) {
        if (value.length() < 6)
            throw new IllegalArgumentException("Password must have at least 6 characters");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
