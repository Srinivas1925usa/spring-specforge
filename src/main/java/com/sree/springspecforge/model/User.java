package com.sree.springspecforge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 * Represents the persistence model of a user in the database.
 * This entity stores user-related data, including sensitive fields like email
 * that might not be exposed directly via API DTOs.
 */
@Entity
@Table(name = "users") // Renamed to 'users' to avoid potential SQL keyword conflicts with 'user'
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role; // e.g., 'ADMIN', 'USER', 'GUEST'

    @Column(nullable = false, unique = true)
    private String email; // Potentially other fields not exposed via DTO

    // Default constructor for JPA
    public User() {
    }

    public User(Long id, String name, String role, String email) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + ''' +
               ", role='" + role + ''' +
               ", email='" + email + ''' +
               '}';
    }
}