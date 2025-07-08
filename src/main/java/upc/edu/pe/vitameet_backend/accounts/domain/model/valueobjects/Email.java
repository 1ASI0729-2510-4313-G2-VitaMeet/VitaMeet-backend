package upc.edu.pe.vitameet_backend.accounts.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Email {

    private String address;

    protected Email() {}

    public Email(String address) {
        if (!address.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$"))
            throw new IllegalArgumentException("Invalid email format");
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
