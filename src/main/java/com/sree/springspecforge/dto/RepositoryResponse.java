package com.sree.springspecforge.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for representing repository details in API responses.
 */
@Data
@Builder
public class RepositoryResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean isPublic;
    private Long ownerId; // Exposing owner ID for simplicity in DTO
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}