package upc.edu.pe.vitameet.profile.domain.model.commands;

public record CreateProfileCommand(String fullName, String email, String phone, String role) {
    public CreateProfileCommand {
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("Full name is required");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Valid email is required");
        if (phone == null || phone.length() < 6) throw new IllegalArgumentException("Phone is required");
        if (role == null || role.isBlank()) throw new IllegalArgumentException("Role is required");
    }
}
