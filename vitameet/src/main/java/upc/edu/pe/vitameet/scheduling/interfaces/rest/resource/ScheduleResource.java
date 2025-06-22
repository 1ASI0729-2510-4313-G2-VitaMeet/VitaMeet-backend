package upc.edu.pe.vitameet.scheduling.interfaces.rest.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResource {
    private Long id;
    private Long doctorId;
    private String availableFrom;
    private String availableTo;
}
