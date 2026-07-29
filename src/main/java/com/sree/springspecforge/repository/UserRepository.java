package com.sree.springspecforge.repository;

import com.sree.springspecforge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entities.
 * Handles data access operations for the User entity, leveraging Spring Data JPA.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA automatically provides methods like findById(Long id)
    // No custom methods are needed for this feature.
}