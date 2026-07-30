package com.sree.springspecforge.controller;

import com.sree.springspecforge.dto.UserDTO;
import com.sree.springspecforge.dto.UserResponseDTO;
import com.sree.springspecforge.model.User;
import com.sree.springspecforge.service.UserService;
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
import java.util.stream.Collectors;

/**
 * REST Controller for managing user-related operations.
 * Handles HTTP requests for creating, retrieving, updating, and deleting users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A ResponseEntity containing a list of User entities and an HTTP status of OK.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        // NOTE: For consistency and best practice, this endpoint could also be updated
        // to return List<UserResponseDTO> by mapping each User entity.
        // For this specific feature, only getUserById was explicitly requested to change.
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves a user by their ID, including associated department details.
     *
     * @param userId The unique identifier of the user to retrieve.
     * @return A ResponseEntity containing the UserResponseDTO and an HTTP status of OK.
     *         Returns 404 NOT FOUND if the user does not exist (handled by GlobalExceptionHandler).
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
        UserResponseDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * Creates a new user.
     *
     * @param userDTO The UserDTO containing the details for the new user.
     * @return A ResponseEntity containing the created User entity and an HTTP status of CREATED.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Updates an existing user identified by their ID.
     *
     * @param userId  The unique identifier of the user to update.
     * @param userDTO The UserDTO containing the updated details for the user.
     * @return A ResponseEntity containing the updated User entity and an HTTP status of OK.
     *         Returns 404 NOT FOUND if the user does not exist (handled by GlobalExceptionHandler).
     */
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Deletes a user identified by their ID.
     *
     * @param userId The unique identifier of the user to delete.
     * @return A ResponseEntity with no content and an HTTP status of NO_CONTENT.
     *         Returns 404 NOT FOUND if the user does not exist (handled by GlobalExceptionHandler).
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}