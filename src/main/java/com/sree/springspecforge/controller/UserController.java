package com.sree.springspecforge.controller;

import com.sree.springspecforge.dto.CreateUserRequest;
import com.sree.springspecforge.dto.UserResponse;
import com.sree.springspecforge.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing user resources.
 * Provides endpoints for creating, retrieving, and listing users.
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor // Generates a constructor for final fields, enabling dependency injection
public class UserController {

    private final UserService userService;

    /**
     * Handles POST requests to create a new user.
     *
     * @param request The {@link CreateUserRequest} containing user details.
     * @return A {@link ResponseEntity} with the created {@link UserResponse} and HTTP status 201 Created.
     */
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    /**
     * Handles GET requests to retrieve all users.
     *
     * @return A {@link ResponseEntity} with a list of {@link UserResponse} and HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Handles GET requests to retrieve a user by their ID.
     *
     * @param id The unique identifier of the user.
     * @return A {@link ResponseEntity} with the {@link UserResponse} of the found user and HTTP status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }
}