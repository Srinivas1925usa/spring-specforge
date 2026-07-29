package com.sree.springspecforge.dto;

import lombok.Data;

/**
 * DTO for updating an existing repository.
 * Fields are optional to allow partial updates (PATCH-like behavior on PUT).
 */
@Data
public class UpdateRepositoryRequest {
    private String name;
    private String description;
    private Boolean isPublic;
}