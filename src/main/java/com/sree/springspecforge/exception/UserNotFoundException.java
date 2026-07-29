package com.sree.springspecforge.exception;

/**
 * Custom exception thrown when a requested user is not found in the system.
 * This helps in specific error handling and mapping to appropriate HTTP status codes.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super("User with ID " + userId + " not found.");
    }
}