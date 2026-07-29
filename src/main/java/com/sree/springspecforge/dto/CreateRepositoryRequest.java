package com.sree.springspecforge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for creating a new repository.
 * Includes validation for name, visibility, and owner ID.
 */
@Data
public class CreateRepositoryRequest {
    @NotBlank(message = "Repository name cannot be blank")
    private String name;
    private String description;

    @NotNull(message = "Visibility (isPublic) cannot be null")
    private Boolean isPublic = false; // Default value as per spec, though still requires explicit true/false

    @NotNull(message = "Owner ID cannot be null")
    private Long ownerId;
}