package upc.edu.pe.vitameet.evaluation.interfaces.rest.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationResource {
    private Long id;
    private Long appointmentId;
    private int rating;
    private String comment;
}
