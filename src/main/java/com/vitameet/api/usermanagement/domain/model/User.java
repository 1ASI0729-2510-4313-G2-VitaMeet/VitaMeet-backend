package com.vitameet.api.usermanagement.domain.model;

import com.vitameet.api.shared.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class User extends BaseEntity {

    @Column(name = "nombre", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    // Constructors
    protected User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Business methods
    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
