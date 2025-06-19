package upc.edu.pe.vitameet_backend.evaluation.domain.model.commands;

public record SubmitEvaluationCommand(int rating, String comment, Long doctorId, Long patientId) {}
