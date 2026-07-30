package com.sree.springspecforge.repository;

import com.sree.springspecforge.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the Department entity.
 * Provides standard CRUD operations for Department objects.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // Custom query methods can be added here if needed
}