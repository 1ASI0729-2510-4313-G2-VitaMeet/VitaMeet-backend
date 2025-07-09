package com.vitameet.api.usermanagement.domain.model;

import com.vitameet.api.shared.domain.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
public class Patient extends BaseEntity {

    @Column(name = "dni", length = 20, unique = true, nullable = false)
    private String dni;

    @Column(name = "nombres", length = 100, nullable = false)
    private String fullName;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "contrasena", length = 255, nullable = false)
    private String password;

    @Column(name = "fecha_nacimiento")
    private LocalDate birthDate;

    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender {
        MASCULINO, FEMENINO, OTRO
    }

    // Constructors
    protected Patient() {
    }

    public Patient(String dni, String fullName, String email, String password,
            LocalDate birthDate, Gender gender) {
        this.dni = dni;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    // Business methods
    public boolean isAdult() {
        return birthDate != null &&
                LocalDate.now().minusYears(18).isAfter(birthDate);
    }

    public int getAge() {
        if (birthDate == null)
            return 0;
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public void updatePersonalInfo(String fullName, String email, LocalDate birthDate, Gender gender) {
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public void changePassword(String newPassword) {
        // In real implementation, you would hash the password
        this.password = newPassword;
    }

    // Getters
    public String getDni() {
        return dni;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }
}
