package com.sree.springspecforge.service;

import com.sree.springspecforge.dto.UserDTO;
import com.sree.springspecforge.dto.UserResponseDTO;
import com.sree.springspecforge.exception.UserNotFoundException;
import com.sree.springspecforge.model.Department;
import com.sree.springspecforge.model.User;
import com.sree.springspecforge.repository.DepartmentRepository;
import com.sree.springspecforge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for managing User entities.
 * Provides business logic for CRUD operations on users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository; // Inject DepartmentRepository

    @Autowired
    public UserService(UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    /**
     * Retrieves all users from the database.
     * For consistency with the `getUserById` endpoint, this method could also return `List<UserResponseDTO>`.
     * However, for now, it returns `List<User>` as per the original structure, noting that
     * converting to DTOs for all endpoints is a best practice.
     *
     * @return A list of all User entities.
     */
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID, including department details, and maps it to a DTO.
     * This method uses the `UserRepository.findById` which has an `@EntityGraph`
     * to eagerly fetch the associated department, preventing `LazyInitializationException`.
     *
     * @param userId The ID of the user to retrieve.
     * @return A UserResponseDTO containing the user and department details.
     * @throws UserNotFoundException If no user with the given ID is found.
     */
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        // Map User entity to UserResponseDTO
        Integer deptno = (user.getDepartment() != null) ? user.getDepartment().getDeptno() : null;
        String deptname = (user.getDepartment() != null) ? user.getDepartment().getDeptname() : null;

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getRole(),
                user.getEmail(),
                deptno,
                deptname
        );
    }

    /**
     * Creates a new user based on the provided UserDTO.
     * Allows assigning a department using `deptno` in the DTO.
     *
     * @param userDTO The DTO containing the user's details.
     * @return The created User entity.
     * @throws IllegalArgumentException If the provided department ID is invalid.
     */
    @Transactional
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());

        if (userDTO.getDeptno() != null) {
            Department department = departmentRepository.findById(userDTO.getDeptno())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid department ID: " + userDTO.getDeptno()));
            user.setDepartment(department);
        }

        return userRepository.save(user);
    }

    /**
     * Updates an existing user with the details from the provided UserDTO.
     * Allows updating or unassigning a user's department using `deptno` in the DTO.
     *
     * @param userId  The ID of the user to update.
     * @param userDTO The DTO containing the updated user details.
     * @return The updated User entity.
     * @throws UserNotFoundException    If no user with the given ID is found.
     * @throws IllegalArgumentException If the provided department ID is invalid.
     */
    @Transactional
    public User updateUser(Long userId, UserDTO userDTO) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setName(userDTO.getName());
                    existingUser.setRole(userDTO.getRole());
                    existingUser.setEmail(userDTO.getEmail());

                    if (userDTO.getDeptno() != null) {
                        Department department = departmentRepository.findById(userDTO.getDeptno())
                                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID: " + userDTO.getDeptno()));
                        existingUser.setDepartment(department);
                    } else {
                        existingUser.setDepartment(null); // Allow unassigning department
                    }

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    /**
     * Deletes a user from the database by their ID.
     *
     * @param userId The ID of the user to delete.
     * @throws UserNotFoundException If no user with the given ID is found.
     */
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
}