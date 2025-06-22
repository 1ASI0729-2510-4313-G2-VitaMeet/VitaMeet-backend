package upc.edu.pe.vitameet.authentication.interfaces.rest.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserResource {
    private Long id;
    private String username;
    private String role;
}
