package com.vitameet.api.medicalstaff.interfaces.rest.dto;

public record CreateDoctorRequest(
    String specialty,
    String medicalLicense,
    String officeLocation
) {}
