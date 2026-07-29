package com.sree.springspecforge.controller;

import com.sree.springspecforge.dto.CreateRepositoryRequest;
import com.sree.springspecforge.dto.RepositoryResponse;
import com.sree.springspecforge.dto.UpdateRepositoryRequest;
import com.sree.springspecforge.service.RepositoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Git repository resources.
 * Provides endpoints for creating, retrieving, updating, and deleting repositories.
 */
@RestController
@RequestMapping("/api/v1/repositories")
@RequiredArgsConstructor // Generates a constructor for final fields, enabling dependency injection
public class RepositoryController {

    private final RepositoryService repositoryService;

    /**
     * Handles POST requests to create a new repository.
     *
     * @param request The {@link CreateRepositoryRequest} containing repository details.
     * @return A {@link ResponseEntity} with the created {@link RepositoryResponse} and HTTP status 201 Created.
     */
    @PostMapping
    public ResponseEntity<RepositoryResponse> createRepository(@Valid @RequestBody CreateRepositoryRequest request) {
        RepositoryResponse repositoryResponse = repositoryService.createRepository(request);
        return new ResponseEntity<>(repositoryResponse, HttpStatus.CREATED);
    }

    /**
     * Handles GET requests to retrieve all repositories.
     *
     * @return A {@link ResponseEntity} with a list of {@link RepositoryResponse} and HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<RepositoryResponse>> getAllRepositories() {
        List<RepositoryResponse> repositories = repositoryService.getAllRepositories();
        return ResponseEntity.ok(repositories);
    }

    /**
     * Handles GET requests to retrieve a repository by its ID.
     *
     * @param id The unique identifier of the repository.
     * @return A {@link ResponseEntity} with the {@link RepositoryResponse} of the found repository and HTTP status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RepositoryResponse> getRepositoryById(@PathVariable Long id) {
        RepositoryResponse repositoryResponse = repositoryService.getRepositoryById(id);
        return ResponseEntity.ok(repositoryResponse);
    }

    /**
     * Handles PUT requests to update an existing repository.
     *
     * @param id The unique identifier of the repository to update.
     * @param request The {@link UpdateRepositoryRequest} containing updated repository details.
     * @return A {@link ResponseEntity} with the updated {@link RepositoryResponse} and HTTP status 200 OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RepositoryResponse> updateRepository(@PathVariable Long id, @RequestBody UpdateRepositoryRequest request) {
        RepositoryResponse repositoryResponse = repositoryService.updateRepository(id, request);
        return ResponseEntity.ok(repositoryResponse);
    }

    /**
     * Handles DELETE requests to delete a repository by its ID.
     *
     * @param id The unique identifier of the repository to delete.
     * @return A {@link ResponseEntity} with no content and HTTP status 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepository(@PathVariable Long id) {
        repositoryService.deleteRepository(id);
        return ResponseEntity.noContent().build();
    }
}