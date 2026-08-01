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
 * Uses {@link EntityGraph} to eagerly fetch department and address associations
 * and avoid {@code LazyInitializationException} when {@code open-in-view=false}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves all users, eagerly fetching associated Department and Address.
     * Prevents LazyInitializationException when serializing users with open-in-view disabled.
     *
     * @return A list of all User entities with their department and address loaded.
     */
    @Override
    @EntityGraph(attributePaths = {"department", "address"})
    List<User> findAll();

    /**
     * Retrieves a user by their ID, eagerly fetching associated Department and Address.
     * This is crucial to prevent LazyInitializationException when accessing nested details
     * outside of a transactional context (or when mapping to DTOs in the service layer).
     *
     * @param id The ID of the user to retrieve.
     * @return An Optional containing the User entity with department and address, if found.
     */
    @Override
    @EntityGraph(attributePaths = {"department", "address"})
    Optional<User> findById(Long id);
}
