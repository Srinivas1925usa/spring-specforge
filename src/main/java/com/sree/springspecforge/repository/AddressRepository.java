package com.sree.springspecforge.repository;

import com.sree.springspecforge.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA repository for the {@link Address} entity.
 * Provides standard CRUD operations for Address objects.
 * Address lifecycle is primarily managed via cascade from {@link com.sree.springspecforge.model.User}.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Finds the address linked to a given user ID (One-to-One).
     *
     * @param userId the user primary key
     * @return an Optional containing the Address if one exists for the user
     */
    Optional<Address> findByUserId(Long userId);
}
