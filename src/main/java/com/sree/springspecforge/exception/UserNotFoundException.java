package com.sree.springspecforge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when a user is not found by their ID.
 * Annotated with {@code @ResponseStatus(HttpStatus.NOT_FOUND)} to automatically
 * map this exception to an HTTP 404 Not Found status when thrown from a controller.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}