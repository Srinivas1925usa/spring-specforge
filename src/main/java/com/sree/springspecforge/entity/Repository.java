package com.sree.springspecforge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Represents a Git repository.
 * Includes details like name, description, visibility, and its owner.
 * Auditing fields for creation and last update timestamps are included.
 */
@Entity
@Table(name = "repositories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Enables automatic population of @CreatedDate and @LastModifiedDate
public class Repository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description; // Description is optional

    @Column(nullable = false)
    private Boolean isPublic = false; // Default to private as per spec's default for 'isPublic'

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Many repositories can belong to one user
    @JoinColumn(name = "owner_id", nullable = false) // Foreign key column
    private User owner;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}