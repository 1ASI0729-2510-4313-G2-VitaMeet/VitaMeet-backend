package upc.edu.pe.vitameet.appointment.interfaces.rest.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResource {
    private Long id;
    private String scheduledAt;
    private String status;
}