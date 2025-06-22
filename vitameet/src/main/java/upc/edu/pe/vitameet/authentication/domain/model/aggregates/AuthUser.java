package upc.edu.pe.vitameet.authentication.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import upc.edu.pe.vitameet.authentication.domain.model.commands.RegisterUserCommand;
import upc.edu.pe.vitameet.authentication.domain.model.valueobjects.Role;
import upc.edu.pe.vitameet.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
@NoArgsConstructor
public class AuthUser extends AuditableAbstractAggregateRoot<AuthUser> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public AuthUser(RegisterUserCommand command) {
        if (command.username().length() < 4)
            throw new IllegalArgumentException("Username must have at least 4 characters.");
        if (command.password().length() < 6)
            throw new IllegalArgumentException("Password must have at least 6 characters.");
        this.username = command.username();
        this.password = command.password(); // Debería estar encriptada en un caso real
        this.role = Role.valueOf(command.role().toUpperCase());
    }
}
