package com.sree.springspecforge.repository;

import com.sree.springspecforge.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for the User entity.
 * Provides standard CRUD operations for User objects.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves all users, eagerly fetching the associated Department.
     * Prevents LazyInitializationException when serializing users with open-in-view disabled.
     *
     * @return A list of all User entities with their departments loaded.
     */
    @Override
    @EntityGraph(attributePaths = "department")
    List<User> findAll();

    /**
     * Retrieves a user by their ID, eagerly fetching the associated Department.
     * This is crucial to prevent LazyInitializationException when accessing department details
     * outside of a transactional context.
     *
     * @param id The ID of the user to retrieve.
     * @return An Optional containing the User entity with its department, if found.
     */
    @Override
    @EntityGraph(attributePaths = "department")
    Optional<User> findById(Long id);
}