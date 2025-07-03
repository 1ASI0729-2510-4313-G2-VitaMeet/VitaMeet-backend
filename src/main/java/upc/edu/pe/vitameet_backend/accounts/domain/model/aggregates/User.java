package upc.edu.pe.vitameet_backend.accounts.domain.model.aggregates;

import jakarta.persistence.*;
import upc.edu.pe.vitameet_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import upc.edu.pe.vitameet_backend.accounts.domain.model.valueobjects.Email;
import upc.edu.pe.vitameet_backend.accounts.domain.model.valueobjects.Password;

@Entity
@Table(name = "users")
public class User extends AuditableAbstractAggregateRoot<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    protected User() {}

    public User(Email email, Password password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
