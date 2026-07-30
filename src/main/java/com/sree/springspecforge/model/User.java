package com.sree.springspecforge.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 * Represents the persistence model of a user in the database.
 * This entity stores user-related data, including sensitive fields like email
 * that might not be exposed directly via API DTOs.
 * <p>
 * Associations:
 * <ul>
 *   <li>{@link Department} — Many-to-One (optional), FK {@code users.deptno}</li>
 *   <li>{@link Address} — One-to-One inverse side; Address owns FK {@code address.user_id}.
 *       CascadeType.ALL + orphanRemoval=true so the address lifecycle follows the user.</li>
 * </ul>
 * </p>
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptno") // This maps to the deptno column in the users table
    private Department department;

    /**
     * Inverse side of the User ↔ Address One-to-One.
     * Owning side is {@link Address#user} ({@code address.user_id}).
     * CascadeType.ALL + orphanRemoval=true: address is created/updated/deleted with the user.
     */
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    // Default constructor for JPA
    public User() {
    }

    public User(Long id, String name, String role, String email) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
    }

    // Constructor including department
    public User(Long id, String name, String role, String email, Department department) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address and maintains bidirectional consistency on the owning side.
     *
     * @param address the address to associate, or null to clear
     */
    public void setAddress(Address address) {
        if (address == null) {
            if (this.address != null) {
                this.address.setUser(null);
            }
        } else {
            address.setUser(this);
        }
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id); // For entities, typically only ID is used for equals/hashCode
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // For entities, typically only ID is used for equals/hashCode
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", department=" + (department != null && org.hibernate.Hibernate.isInitialized(department)
                        ? department.getDeptname() : "null") +
                ", address=" + (address != null && org.hibernate.Hibernate.isInitialized(address)
                        ? address.getId() : "null") +
                '}';
    }
}
