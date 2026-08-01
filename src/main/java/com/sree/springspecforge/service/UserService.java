package com.sree.springspecforge.service;

import com.sree.springspecforge.dto.AddressDTO;
import com.sree.springspecforge.dto.DepartmentDTO;
import com.sree.springspecforge.dto.UserDTO;
import com.sree.springspecforge.dto.UserResponseDTO;
import com.sree.springspecforge.exception.ResourceNotFoundException;
import com.sree.springspecforge.model.Address;
import com.sree.springspecforge.model.Department;
import com.sree.springspecforge.model.User;
import com.sree.springspecforge.repository.DepartmentRepository;
import com.sree.springspecforge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id The UUID of the user to retrieve.
     * @return UserResponseDTO containing the user's details.
     * @throws ResourceNotFoundException if the user with the given ID is not found.
     */
    public UserResponseDTO getUserById(UUID id) {
        log.debug("Fetching user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return mapToUserResponseDTO(user);
    }

    /**
     * Retrieves all users with pagination.
     *
     * @param pageable Pagination information.
     * @return A page of UserResponseDTOs.
     */
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        log.debug("Fetching all users with pagination: {}", pageable);
        return userRepository.findAll(pageable)
                .map(this::mapToUserResponseDTO);
    }

    /**
     * Creates a new user.
     *
     * @param userDTO User data for creation.
     * @return UserResponseDTO of the created user.
     */
    @Transactional
    public UserResponseDTO createUser(UserDTO userDTO) {
        log.info("Creating new user with email: {}", userDTO.getEmail());
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        // createdAt and updatedAt are handled by AuditingEntityListener
        // Salary is now derived from Department, so not set directly on User.

        // Handle address if present
        if (userDTO.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userDTO.getAddress().getStreet());
            address.setCity(userDTO.getAddress().getCity());
            address.setState(userDTO.getAddress().getState());
            address.setZipCode(userDTO.getAddress().getZipCode());
            user.setAddress(address); // Set bidirectionality
        }

        // Handle department if present
        if (userDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(userDTO.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + userDTO.getDepartmentId()));
            user.setDepartment(department);
        }

        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        return mapToUserResponseDTO(savedUser);
    }

    /**
     * Updates an existing user.
     *
     * @param id      The UUID of the user to update.
     * @param userDTO New user data.
     * @return UserResponseDTO of the updated user.
     * @throws ResourceNotFoundException if the user with the given ID is not found.
     */
    @Transactional
    public UserResponseDTO updateUser(UUID id, UserDTO userDTO) {
        log.info("Updating user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        // Salary is derived from Department, so not set directly on User.

        // Update address if present
        if (userDTO.getAddress() != null) {
            if (user.getAddress() == null) {
                user.setAddress(new Address());
            }
            user.getAddress().setStreet(userDTO.getAddress().getStreet());
            user.getAddress().setCity(userDTO.getAddress().getCity());
            user.getAddress().setState(userDTO.getAddress().getState());
            user.getAddress().setZipCode(userDTO.getAddress().getZipCode());
        } else if (user.getAddress() != null) {
            // If address is removed from DTO, remove it from entity
            user.setAddress(null);
        }

        // Update department if present.
        if (userDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(userDTO.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + userDTO.getDepartmentId()));
            user.setDepartment(department);
        } else {
            user.setDepartment(null); // Allow unassigning department
        }

        User updatedUser = userRepository.save(user);
        log.info("User with ID: {} updated successfully.", updatedUser.getId());
        return mapToUserResponseDTO(updatedUser);
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id The UUID of the user to delete.
     * @throws ResourceNotFoundException if the user with the given ID is not found.
     */
    @Transactional
    public void deleteUser(UUID id) {
        log.info("Deleting user with ID: {}", id);
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
        log.info("User with ID: {} deleted successfully.", id);
    }

    /**
     * Maps a User entity to a UserResponseDTO.
     *
     * @param user The User entity to map.
     * @return The corresponding UserResponseDTO.
     */
    private UserResponseDTO mapToUserResponseDTO(User user) {
        UserResponseDTO.UserResponseDTOBuilder builder = UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt());

        // Derive top-level salary from the associated Department
        if (user.getDepartment() != null) {
            builder.salary(user.getDepartment().getSalary());
        } else {
            builder.salary(null); // Explicitly set to null if no department or salary
        }

        // Map address if present
        if (user.getAddress() != null) {
            builder.address(AddressDTO.builder()
                    .id(user.getAddress().getId())
                    .street(user.getAddress().getStreet())
                    .city(user.getAddress().getCity())
                    .state(user.getAddress().getState())
                    .zipCode(user.getAddress().getZipCode())
                    .build());
        }

        // Map department if present, including its salary
        if (user.getDepartment() != null) {
            builder.department(DepartmentDTO.builder()
                    .id(user.getDepartment().getId())
                    .name(user.getDepartment().getName())
                    .location(user.getDepartment().getLocation())
                    .salary(user.getDepartment().getSalary()) // Map department's salary
                    .build());
        }

        return builder.build();
    }
}