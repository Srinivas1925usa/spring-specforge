package com.sree.springspecforge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    // One-to-one relationship with Address
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Address address;

    // Many-to-one relationship with Department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id") // This now correctly points to UUID Department.id
    private Department department;

    // Helper methods for relationships to ensure bidirectionality and prevent orphan records
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

    public void setDepartment(Department department) {
        // Remove from old department if exists
        if (this.department != null && this.department.getUsers().contains(this)) {
            this.department.getUsers().remove(this);
        }
        this.department = department;
        // Add to new department
        if (department != null && !department.getUsers().contains(this)) {
            department.getUsers().add(this);
        }
    }
}