package upc.edu.pe.vitameet.profile.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import upc.edu.pe.vitameet.profile.domain.model.commands.CreateProfileCommand;
import upc.edu.pe.vitameet.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
@NoArgsConstructor
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String role;

    public Profile(CreateProfileCommand command) {
        this.fullName = command.fullName();
        this.email = command.email();
        this.phone = command.phone();
        this.role = command.role();
    }

    public void updateProfile(String fullName, String phone) {
        if (fullName != null && !fullName.isBlank()) this.fullName = fullName;
        if (phone != null && !phone.isBlank()) this.phone = phone;
    }
}
