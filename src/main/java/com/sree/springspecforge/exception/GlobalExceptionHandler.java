package com.sree.springspecforge.exception;

import com.sree.springspecforge.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

/**
 * Global exception handler to provide consistent error responses across the application.
 * It uses {@code @RestControllerAdvice} to centralize the handling of exceptions
 * thrown by {@code @RequestMapping} methods.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles UserNotFoundException, returning a 404 Not Found status.
     *
     * @param ex The UserNotFoundException instance.
     * @param request The HTTP servlet request.
     * @return A ResponseEntity containing an ErrorResponse with 404 status.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles bean validation failures from {@code @Valid} request bodies.
     *
     * @param ex The MethodArgumentNotValidException instance.
     * @param request The HTTP servlet request.
     * @return A ResponseEntity containing an ErrorResponse with 400 status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        if (errorMessage.isBlank()) {
            errorMessage = "Validation failed";
        }

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessage,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MethodArgumentTypeMismatchException, returning a 400 Bad Request status.
     * This occurs when a path variable or request parameter cannot be converted
     * to the expected type (e.g., non-numeric string for a Long ID).
     *
     * @param ex The MethodArgumentTypeMismatchException instance.
     * @param request The HTTP servlet request.
     * @return A ResponseEntity containing an ErrorResponse with 400 status.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getName() : "unknown";
        Object value = ex.getValue();
        String valueType = value != null ? value.getClass().getName() : "null";
        String errorMessage = String.format(
                "Type mismatch. Failed to convert value of type '%s' to required type '%s' for '%s'",
                valueType, requiredType, ex.getName());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessage,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles illegal arguments (e.g. invalid department ID), returning 400 Bad Request.
     *
     * @param ex The IllegalArgumentException instance.
     * @param request The HTTP servlet request.
     * @return A ResponseEntity containing an ErrorResponse with 400 status.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic unexpected exceptions, returning a 500 Internal Server Error status.
     * This acts as a fallback for any exceptions not specifically handled by other methods.
     *
     * @param ex The Exception instance.
     * @param request The HTTP servlet request.
     * @return A ResponseEntity containing an ErrorResponse with 500 status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred: " + ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}