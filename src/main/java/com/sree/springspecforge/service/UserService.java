package com.sree.springspecforge.service;

import com.sree.springspecforge.dto.CreateUserRequest;
import com.sree.springspecforge.dto.UserResponse;

import java.util.List;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {
    /**
     * Creates a new user based on the provided request data.
     * @param request The DTO containing user creation details.
     * @return A {@link UserResponse} object representing the newly created user.
     */
    UserResponse createUser(CreateUserRequest request);

    /**
     * Retrieves a list of all users in the system.
     * @return A list of {@link UserResponse} objects.
     */
    List<UserResponse> getAllUsers();

    /**
     * Retrieves a single user by their unique identifier.
     * @param id The ID of the user to retrieve.
     * @return A {@link UserResponse} object representing the found user.
     */
    UserResponse getUserById(Long id);
}