package com.sree.springspecforge.service;

import com.sree.springspecforge.dto.DepartmentDTO;
import com.sree.springspecforge.exception.NotFoundException;
import com.sree.springspecforge.model.Department;
import com.sree.springspecforge.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for managing Department entities.
 * Provides business logic for CRUD operations on departments.
 */
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private static final BigDecimal DEFAULT_SALARY = new BigDecimal("10000.00");

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Retrieves all departments.
     *
     * @return A list of all DepartmentDTOs.
     */
    @Transactional(readOnly = true)
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a department by its ID.
     *
     * @param id The unique identifier of the department.
     * @return The DepartmentDTO corresponding to the ID.
     * @throws NotFoundException if the department does not exist.
     */
    @Transactional(readOnly = true)
    public DepartmentDTO getDepartmentById(Integer id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + id));
        return convertToDto(department);
    }

    /**
     * Creates a new department based on the provided DTO.
     * If salary is not provided in the DTO, it defaults to 10000.00.
     * If location is not provided in the DTO, it defaults to "Unknown".
     *
     * @param departmentDTO The DTO containing the details for the new department.
     * @return The created DepartmentDTO.
     */
    @Transactional
    public DepartmentDTO createDepartment(@Valid DepartmentDTO departmentDTO) {
        Department department = convertToEntity(departmentDTO);
        
        // Handle default salary for creation
        if (department.getSalary() == null) {
            department.setSalary(DEFAULT_SALARY);
        }
        // Ensure location is not null or blank, fallback to a default
        if (department.getLocation() == null || department.getLocation().isBlank()) {
            department.setLocation("Unknown");
        }

        Department savedDepartment = departmentRepository.save(department);
        return convertToDto(savedDepartment);
    }

    /**
     * Updates an existing department identified by its ID.
     * If a field (deptname, location, salary) is not provided in the DTO, the existing value remains unchanged.
     *
     * @param id The unique identifier of the department to update.
     * @param departmentDTO The DTO containing the updated details.
     * @return The updated DepartmentDTO.
     * @throws NotFoundException if the department does not exist.
     */
    @Transactional
    public DepartmentDTO updateDepartment(Integer id, DepartmentDTO departmentDTO) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + id));

        // Update fields only if they are provided in the DTO and are not blank (for strings)
        Optional.ofNullable(departmentDTO.getDeptname())
                .filter(name -> !name.isBlank())
                .ifPresent(existingDepartment::setDeptname);

        Optional.ofNullable(departmentDTO.getLocation())
                .filter(location -> !location.isBlank())
                .ifPresent(existingDepartment::setLocation);

        Optional.ofNullable(departmentDTO.getSalary())
                .ifPresent(existingDepartment::setSalary);

        Department updatedDepartment = departmentRepository.save(existingDepartment);
        return convertToDto(updatedDepartment);
    }

    /**
     * Deletes a department by its ID.
     *
     * @param id The unique identifier of the department to delete.
     * @throws NotFoundException if the department does not exist.
     */
    @Transactional
    public void deleteDepartment(Integer id) {
        if (!departmentRepository.existsById(id)) {
            throw new NotFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    /**
     * Converts a Department entity to a DepartmentDTO.
     *
     * @param department The Department entity to convert.
     * @return The corresponding DepartmentDTO.
     */
    private DepartmentDTO convertToDto(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDeptno(department.getDeptno());
        dto.setDeptname(department.getDeptname());
        dto.setLocation(department.getLocation()); // Include new location field
        dto.setSalary(department.getSalary());     // Include new salary field
        return dto;
    }

    /**
     * Converts a DepartmentDTO to a Department entity.
     *
     * @param departmentDTO The DepartmentDTO to convert.
     * @return The corresponding Department entity.
     */
    private Department convertToEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setDeptno(departmentDTO.getDeptno());
        department.setDeptname(departmentDTO.getDeptname());
        department.setLocation(departmentDTO.getLocation()); // Include new location field
        department.setSalary(departmentDTO.getSalary());     // Include new salary field
        return department;
    }
}