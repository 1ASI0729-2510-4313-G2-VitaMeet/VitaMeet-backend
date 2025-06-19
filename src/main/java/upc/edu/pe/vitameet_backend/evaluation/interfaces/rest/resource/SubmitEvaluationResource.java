package upc.edu.pe.vitameet_backend.evaluation.interfaces.rest.resource;

public record SubmitEvaluationResource(int rating, String comment, Long doctorId, Long patientId) {}
