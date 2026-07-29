package com.sree.springspecforge.dto;

import java.util.Objects;

/**
 * Represents the data structure for the API response when fetching user details.
 * This DTO exposes only specific fields (id, name, role, email) from the User entity
 * to clients, ensuring data encapsulation and API contract adherence.
 */
public class UserDTO {
    private Long id;
    private String name;
    private String role;
    private String email; // New field added

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String role, String email) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email; // Initialize new field
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

    // Getter for new email field
    public String getEmail() {
        return email;
    }

    // Setter for new email field
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(name, userDTO.name) && Objects.equals(role, userDTO.role) && Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role, email);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
               "id=" + id +
               ", name='" + name + "' " +
               ", role='" + role + "' " +
               ", email='" + email + "' " +
               '}';
    }
}
