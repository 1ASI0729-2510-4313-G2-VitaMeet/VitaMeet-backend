package com.vitameet.api.medicalstaff.domain.model;

import com.vitameet.api.shared.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "medicos")
public class Doctor extends BaseEntity {

    @Column(name = "especialidad", length = 100, nullable = false)
    private String specialty;

    @Column(name = "crmp", length = 20, unique = true, nullable = false)
    private String medicalLicense;

    @Column(name = "ubicacion_consultorio", length = 255)
    private String officeLocation;

    // Constructors
    protected Doctor() {
    }

    public Doctor(String specialty, String medicalLicense, String officeLocation) {
        this.specialty = specialty;
        this.medicalLicense = medicalLicense;
        this.officeLocation = officeLocation;
    }

    // Business methods
    public void updateProfessionalInfo(String specialty, String officeLocation) {
        this.specialty = specialty;
        this.officeLocation = officeLocation;
    }

    public boolean canTreatSpecialty(String requiredSpecialty) {
        return this.specialty.equalsIgnoreCase(requiredSpecialty);
    }

    // Getters
    public String getSpecialty() {
        return specialty;
    }

    public String getMedicalLicense() {
        return medicalLicense;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }
}
