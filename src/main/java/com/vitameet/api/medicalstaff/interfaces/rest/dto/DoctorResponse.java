package com.vitameet.api.medicalstaff.interfaces.rest.dto;

import com.vitameet.api.medicalstaff.domain.model.Doctor;

public record DoctorResponse(
    Long id,
    String specialty,
    String medicalLicense,
    String officeLocation
) {
    public static DoctorResponse from(Doctor doctor) {
        return new DoctorResponse(
            doctor.getId(),
            doctor.getSpecialty(),
            doctor.getMedicalLicense(),
            doctor.getOfficeLocation()
        );
    }
}
