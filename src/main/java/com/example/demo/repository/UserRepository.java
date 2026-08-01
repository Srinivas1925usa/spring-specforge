package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity.
 * Provides standard CRUD operations and custom query capabilities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository already provides the count() method, so no custom method declaration is needed here.
    // You can add custom query methods here if needed, e.g., findByEmail(String email);
}