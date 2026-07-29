package com.sree.springspecforge.service;

import com.sree.springspecforge.dto.CreateRepositoryRequest;
import com.sree.springspecforge.dto.RepositoryResponse;
import com.sree.springspecforge.dto.UpdateRepositoryRequest;

import java.util.List;

/**
 * Service interface for managing Git repository-related operations.
 */
public interface RepositoryService {
    /**
     * Creates a new repository based on the provided request data.
     * @param request The DTO containing repository creation details.
     * @return A {@link RepositoryResponse} object representing the newly created repository.
     */
    RepositoryResponse createRepository(CreateRepositoryRequest request);

    /**
     * Retrieves a list of all repositories in the system.
     * @return A list of {@link RepositoryResponse} objects.
     */
    List<RepositoryResponse> getAllRepositories();

    /**
     * Retrieves a single repository by its unique identifier.
     * @param id The ID of the repository to retrieve.
     * @return A {@link RepositoryResponse} object representing the found repository.
     */
    RepositoryResponse getRepositoryById(Long id);

    /**
     * Updates an existing repository with the provided details.
     * Only fields present in the request will be updated.
     * @param id The ID of the repository to update.
     * @param request The DTO containing fields to update.
     * @return A {@link RepositoryResponse} object representing the updated repository.
     */
    RepositoryResponse updateRepository(Long id, UpdateRepositoryRequest request);

    /**
     * Deletes a repository by its unique identifier.
     * @param id The ID of the repository to delete.
     */
    void deleteRepository(Long id);
}