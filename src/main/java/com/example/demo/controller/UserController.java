package com.example.demo.controller;

import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing User-related operations.
 */
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves the total count of user records in the database.
     *
     * @return A ResponseEntity containing the total user count as a long.
     */
    @Operation(summary = "Get total count of users", description = "Retrieves the total number of user records currently stored in the database.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Total user count retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "integer", format = "int64", example = "123"))),
                    @ApiResponse(responseCode = "500", description = "Internal server error during count retrieval")
            })
    @GetMapping("/count")
    public ResponseEntity<Long> getTotalUserCount() {
        log.info("Request received to get total user count.");
        try {
            long count = userService.getTotalUserCount();
            log.info("Successfully retrieved total user count: {}", count);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            log.error("Error retrieving total user count: {}", e.getMessage(), e);
            // MINOR: For a production application, consider returning a consistent Error DTO
            // instead of just .build() to provide more context to the API consumer.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Existing user-related endpoints would also be here (e.g., createUser, getUserById, etc.)
}