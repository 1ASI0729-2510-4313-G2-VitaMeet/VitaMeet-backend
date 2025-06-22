package upc.edu.pe.vitameet.evaluation.domain.model.commands;

public record CreateEvaluationCommand(Long appointmentId, int rating, String comment) {
    public CreateEvaluationCommand {
        if (appointmentId == null || appointmentId <= 0)
            throw new IllegalArgumentException("AppointmentId must be a positive number");
        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        if (comment != null && comment.length() > 500)
            throw new IllegalArgumentException("Comment must be 500 characters or less");
    }
}
