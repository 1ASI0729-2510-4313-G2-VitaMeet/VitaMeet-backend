package upc.edu.pe.vitameet_backend.evaluation.interfaces.rest.transform;

import upc.edu.pe.vitameet_backend.evaluation.domain.model.commands.SubmitEvaluationCommand;
import upc.edu.pe.vitameet_backend.evaluation.interfaces.rest.resource.SubmitEvaluationResource;

public class EvaluationResourceAssembler {
    public static SubmitEvaluationCommand toCommandFromResource(SubmitEvaluationResource resource) {
        return new SubmitEvaluationCommand(
                resource.rating(),
                resource.comment(),
                resource.doctorId(),
                resource.patientId()
        );
    }
}
