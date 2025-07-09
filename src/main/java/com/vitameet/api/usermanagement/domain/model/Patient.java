package com.vitameet.api.usermanagement.domain.model;

import com.vitameet.api.shared.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "role", length = 20, nullable = false)
    private String role = "Paciente";

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "diagnosis", length = 500)
    private String diagnosis;

    @Column(name = "treatment", length = 500)
    private String treatment;

    @Column(name = "date", length = 50)
    private String date;

    // Constructors
    protected Patient() {
    }

    public Patient(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "Paciente";
    }

    public Patient(String name, String email, String password, Integer age, String phone,
            String address, String diagnosis, String treatment, String date) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "Paciente";
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.date = date;
    }

    // Business methods
    public void updateProfile(String name, String email, Integer age, String phone,
            String address, String diagnosis, String treatment, String date) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.date = date;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
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

    public Integer getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getDate() {
        return date;
    }
}
