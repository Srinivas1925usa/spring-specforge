package com.sree.springspecforge.dto;

import java.util.Objects;

/**
 * Represents the data structure for the API response when fetching user details.
 * This DTO exposes only specific fields (id, name, role) from the User entity
 * to clients, ensuring data encapsulation and API contract adherence.
 */
public class UserDTO {
    private Long id;
    private String name;
    private String role;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(name, userDTO.name) && Objects.equals(role, userDTO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
               "id=" + id +
               ", name='" + name + ''' +
               ", role='" + role + ''' +
               '}';
    }
}