package com.sree.springspecforge.service;

import com.sree.springspecforge.dto.DepartmentDTO;
import com.sree.springspecforge.model.Department;
import com.sree.springspecforge.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service layer for managing Department entities.
 * Provides business logic for CRUD operations on departments.
 */
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Fetches a department by its ID.
     * @param deptno The ID of the department.
     * @return An Optional containing the Department if found, or empty otherwise.
     */
    @Transactional(readOnly = true)
    public Optional<Department> getDepartmentById(Integer deptno) {
        return departmentRepository.findById(deptno);
    }

    /**
     * Creates a new department from a DTO.
     * @param departmentDTO The DTO containing department details.
     * @return The created Department entity.
     * @throws IllegalArgumentException if a department with the given ID already exists.
     */
    @Transactional
    public Department createDepartment(DepartmentDTO departmentDTO) {
        if (departmentDTO.getDeptno() != null && departmentRepository.existsById(departmentDTO.getDeptno())) {
            throw new IllegalArgumentException("Department with ID " + departmentDTO.getDeptno() + " already exists.");
        }
        Department department = new Department(departmentDTO.getDeptno(), departmentDTO.getDeptname());
        return departmentRepository.save(department);
    }

    /**
     * Updates an existing department.
     * @param deptno The ID of the department to update.
     * @param departmentDTO The DTO containing the updated department details.
     * @return The updated Department entity.
     * @throws IllegalArgumentException if the department with the given ID is not found.
     */
    @Transactional
    public Department updateDepartment(Integer deptno, DepartmentDTO departmentDTO) {
        return departmentRepository.findById(deptno).map(existingDepartment -> {
            existingDepartment.setDeptname(departmentDTO.getDeptname());
            return departmentRepository.save(existingDepartment);
        }).orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + deptno));
    }

    /**
     * Deletes a department by its ID.
     * @param deptno The ID of the department to delete.
     * @throws IllegalArgumentException if the department with the given ID is not found.
     */
    @Transactional
    public void deleteDepartment(Integer deptno) {
        if (!departmentRepository.existsById(deptno)) {
            throw new IllegalArgumentException("Department not found with ID: " + deptno);
        }
        departmentRepository.deleteById(deptno);
    }
}