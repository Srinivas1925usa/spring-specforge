package com.sree.springspecforge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a Department entity in the system.
 * This entity maps to the 'departments' table in the database.
 */
@Data // Includes @Getter, @Setter, @ToString
@NoArgsConstructor
@AllArgsConstructor // Will generate constructor for all fields
@EqualsAndHashCode(of = "id") // Only ID for equals/hashCode for entity consistency
@Entity
@Table(name = "departments") // Renamed table from 'dept' to 'departments'
public class Department {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id; // Changed from Integer deptno to UUID id

    @Column(name = "name", nullable = false, unique = true) // Changed from deptname to name
    private String name;

    @Column(name = "location") // Assuming location can be nullable if not explicitly required
    private String location;

    @Column(name = "salary", precision = 19, scale = 2) // Added/Updated salary field
    private BigDecimal salary;

    // Bidirectional relationship with User
    // Use mappedBy to indicate the owning side is in User entity (user.department)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    // Custom constructor excluding the 'users' list if needed,
    // or rely on Lombok @AllArgsConstructor for full constructor.
    // For DTO mapping and entity creation, often a constructor for fields is useful.
    public Department(UUID id, String name, String location, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.salary = salary;
    }

    // Helper methods for managing the bidirectional relationship with User
    public void addUser(User user) {
        if (user != null && !this.users.contains(user)) {
            this.users.add(user);
            user.setDepartment(this); // Ensures bidirectional link
        }
    }

    public void removeUser(User user) {
        if (user != null && this.users.contains(user)) {
            this.users.remove(user);
            user.setDepartment(null); // Ensures bidirectional link
        }
    }
}