package com.sree.springspecforge.controller;

import com.sree.springspecforge.dto.UserDTO;
import com.sree.springspecforge.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for managing user-related API endpoints.
 * Handles incoming HTTP requests and delegates business logic to the UserService.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructs a UserController with the given UserService.
     * @param userService The service layer for user operations.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles GET requests to retrieve user details by ID.
     *
     * @param userId The unique identifier of the user to fetch, provided as a path variable.
     * @return A {@link ResponseEntity} containing the {@link UserDTO} of the found user
     *         and a 200 OK status, or an appropriate error response if the user is not found
     *         or the ID format is invalid.
     *
     * <p>Example success response:</p>
     * <pre>
     * {@code
     * HTTP/1.1 200 OK
     * Content-Type: application/json
     *
     * {
     *   "id": 101,
     *   "name": "John Doe",
     *   "role": "USER"
     * }
     * }
     * </pre>
     *
     * <p>Example 404 Not Found response:</p>
     * <pre>
     * {@code
     * HTTP/1.1 404 Not Found
     * Content-Type: application/json
     *
     * {
     *   "timestamp": "2023-10-27T10:30:00.000+00:00",
     *   "status": 404,
     *   "error": "Not Found",
     *   "message": "User with ID 101 not found.",
     *   "path": "/api/users/101"
     * }
     * }
     * </pre>
     *
     * <p>Example 400 Bad Request response for invalid ID format:</p>
     * <pre>
     * {@code
     * HTTP/1.1 400 Bad Request
     * Content-Type: application/json
     *
     * {
     *   "timestamp": "2023-10-27T10:30:00.000+00:00",
     *   "status": 400,
     *   "error": "Bad Request",
     *   "message": "Type mismatch. Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long' for 'userId'",
     *   "path": "/api/users/abc"
     * }
     * }
     * </pre>
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = userService.findById(userId);
        return ResponseEntity.ok(userDTO);
    }
}