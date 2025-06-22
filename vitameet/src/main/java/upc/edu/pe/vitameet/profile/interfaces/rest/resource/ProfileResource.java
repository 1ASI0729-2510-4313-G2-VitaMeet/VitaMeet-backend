package upc.edu.pe.vitameet.profile.interfaces.rest.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResource {
    private Long id;
    private String fullName;
    private String email;
    private String role;
}
