package com.vitameet.api.medicalstaff.domain.model;

import com.vitameet.api.shared.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor extends BaseEntity {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "role", length = 20, nullable = false)
    private String role = "Médico";

    @Column(name = "specialty", length = 100, nullable = false)
    private String specialty;

    @Column(name = "license", length = 20, unique = true, nullable = false)
    private String license;

    @Column(name = "experience")
    private Integer experience;

    // Constructors
    protected Doctor() {
    }

    public Doctor(String name, String email, String password, String specialty, String license, Integer experience) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "Médico";
        this.specialty = specialty;
        this.license = license;
        this.experience = experience;
    }

    // Business methods
    public void updateProfessionalInfo(String name, String email, String specialty, Integer experience) {
        this.name = name;
        this.email = email;
        this.specialty = specialty;
        this.experience = experience;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public boolean canTreatSpecialty(String requiredSpecialty) {
        return this.specialty.equalsIgnoreCase(requiredSpecialty);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getLicense() {
        return license;
    }

    public Integer getExperience() {
        return experience;
    }
}

