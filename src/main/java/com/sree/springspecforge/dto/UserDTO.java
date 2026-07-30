package com.sree.springspecforge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Data Transfer Object for User creation and update requests.
 * This DTO includes validation annotations to ensure data integrity
 * before processing by the service layer.
 */
public class UserDTO {

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;

    @NotBlank(message = "Role cannot be blank")
    @Size(max = 255, message = "Role cannot exceed 255 characters")
    private String role;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    private String email;

    private Integer deptno; // Optional department number for assignment/update

    /**
     * Default constructor.
     */
    public UserDTO() {
    }

    /**
     * Parameterized constructor for creating a UserDTO.
     * @param name The name of the user.
     * @param role The role of the user.
     * @param email The email of the user.
     * @param deptno The department number for the user (can be null).
     */
    public UserDTO(String name, String role, String email, Integer deptno) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.deptno = deptno;
    }

    // Getters

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public Integer getDeptno() {
        return deptno;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(name, userDTO.name) && Objects.equals(role, userDTO.role) && Objects.equals(email, userDTO.email) && Objects.equals(deptno, userDTO.deptno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role, email, deptno);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
               ", deptno=" + deptno +
               '}';
    }
}