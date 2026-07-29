package com.sree.springspecforge.repository;

import com.sree.springspecforge.entity.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for {@link Repository} entities.
 * Provides standard CRUD operations and custom query methods for finding repositories by name,
 * and checking for existence of a name, especially during updates to ensure uniqueness.
 */
@Repository
public interface RepositoryRepository extends JpaRepository<Repository, Long> {
    Optional<Repository> findByName(String name);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id); // For uniqueness check during update, excluding current entity
}