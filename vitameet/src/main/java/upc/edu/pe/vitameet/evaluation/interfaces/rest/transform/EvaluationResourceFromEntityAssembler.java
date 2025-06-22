package upc.edu.pe.vitameet.evaluation.interfaces.rest.transform;

import upc.edu.pe.vitameet.evaluation.domain.model.aggregates.Evaluation;
import upc.edu.pe.vitameet.evaluation.interfaces.rest.resource.EvaluationResource;

public class EvaluationResourceFromEntityAssembler {
    public static EvaluationResource toResource(Evaluation entity) {
        return new EvaluationResource(
                entity.getId(),
                entity.getAppointmentId(),
                entity.getRating(),
                entity.getComment()
        );
    }
}
