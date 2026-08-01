package com.sree.springspecforge.controller;

import com.sree.springspecforge.dto.DepartmentDTO;
import com.sree.springspecforge.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for managing department-related operations.
 * Handles HTTP requests for creating, retrieving, updating, and deleting departments.
 */
@RestController
@RequestMapping("/api/depts")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Retrieves a list of all departments.
     *
     * @return A ResponseEntity containing a list of DepartmentDTOs and an HTTP status of OK.
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    /**
     * Retrieves a department by its ID, including its salary and location.
     *
     * @param id The unique identifier of the department to retrieve.
     * @return A ResponseEntity containing the DepartmentDTO and an HTTP status of OK.
     *         Returns 404 NOT FOUND if the department does not exist (handled by GlobalExceptionHandler).
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Integer id) {
        DepartmentDTO department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    /**
     * Creates a new department.
     * If 'salary' is not provided in the request body, it defaults to 10000.00.
     * If 'location' is not provided in the request body, it defaults to "Unknown".
     *
     * @param departmentDTO The DepartmentDTO containing the details for the new department.
     * @return A ResponseEntity containing the created DepartmentDTO and an HTTP status of CREATED.
     */
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    /**
     * Updates an existing department identified by its ID.
     * If a field (deptname, location, salary) is not provided in the request body, the existing value remains unchanged.
     *
     * @param id The unique identifier of the department to update.
     * @param departmentDTO The DepartmentDTO containing the updated details for the department.
     * @return A ResponseEntity containing the updated DepartmentDTO and an HTTP status of OK.
     *         Returns 404 NOT FOUND if the department does not exist (handled by GlobalExceptionHandler).
     */
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Integer id, @Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(id, departmentDTO);
        return ResponseEntity.ok(updatedDepartment);
    }

    /**
     * Deletes a department identified by its ID.
     *
     * @param id The unique identifier of the department to delete.
     * @return A ResponseEntity with no content and an HTTP status of NO_CONTENT.
     *         Returns 404 NOT FOUND if the department does not exist (handled by GlobalExceptionHandler).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}