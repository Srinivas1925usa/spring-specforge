package com.sree.springspecforge.service.impl;

import com.sree.springspecforge.dto.CreateRepositoryRequest;
import com.sree.springspecforge.dto.RepositoryResponse;
import com.sree.springspecforge.dto.UpdateRepositoryRequest;
import com.sree.springspecforge.entity.Repository;
import com.sree.springspecforge.entity.User;
import com.sree.springspecforge.exception.BadRequestException;
import com.sree.springspecforge.exception.ResourceNotFoundException;
import com.sree.springspecforge.repository.RepositoryRepository;
import com.sree.springspecforge.repository.UserRepository;
import com.sree.springspecforge.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link RepositoryService} interface.
 * Handles business logic for repository management, including validation and interaction with
 * {@link RepositoryRepository} and {@link UserRepository}.
 */
@Service
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields)
public class RepositoryServiceImpl implements RepositoryService {

    private final RepositoryRepository repositoryRepository;
    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     *
     * Ensures repository name is unique and the owner exists before creating the repository.
     * @throws BadRequestException if repository name already exists.
     * @throws ResourceNotFoundException if the specified owner ID does not exist.
     */
    @Override
    @Transactional
    public RepositoryResponse createRepository(CreateRepositoryRequest request) {
        if (repositoryRepository.existsByName(request.getName())) {
            throw new BadRequestException("Repository with name '" + request.getName() + "' already exists.");
        }

        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner with ID " + request.getOwnerId() + " not found."));

        Repository repository = new Repository();
        repository.setName(request.getName());
        repository.setDescription(request.getDescription());
        repository.setIsPublic(request.getIsPublic());
        repository.setOwner(owner);
        // createdAt and updatedAt automatically handled by @EnableJpaAuditing

        Repository savedRepository = repositoryRepository.save(repository);
        return mapRepositoryToRepositoryResponse(savedRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<RepositoryResponse> getAllRepositories() {
        return repositoryRepository.findAll().stream()
                .map(this::mapRepositoryToRepositoryResponse)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     *
     * @throws ResourceNotFoundException if no repository exists with the given ID.
     */
    @Override
    @Transactional(readOnly = true)
    public RepositoryResponse getRepositoryById(Long id) {
        Repository repository = repositoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repository with ID " + id + " not found."));
        return mapRepositoryToRepositoryResponse(repository);
    }

    /**
     * {@inheritDoc}
     *
     * Allows partial updates. Only provided fields are updated.
     * @throws ResourceNotFoundException if no repository exists with the given ID.
     * @throws BadRequestException if the new name (if provided) already exists for another repository.
     */
    @Override
    @Transactional
    public RepositoryResponse updateRepository(Long id, UpdateRepositoryRequest request) {
        Repository existingRepository = repositoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repository with ID " + id + " not found."));

        if (request.getName() != null && !request.getName().isBlank()) {
            if (repositoryRepository.existsByNameAndIdNot(request.getName(), id)) {
                throw new BadRequestException("Repository with name '" + request.getName() + "' already exists.");
            }
            existingRepository.setName(request.getName());
        }

        if (request.getDescription() != null) {
            existingRepository.setDescription(request.getDescription());
        }

        if (request.getIsPublic() != null) {
            existingRepository.setIsPublic(request.getIsPublic());
        }
        // updatedAt is automatically handled by @EnableJpaAuditing

        Repository updatedRepository = repositoryRepository.save(existingRepository);
        return mapRepositoryToRepositoryResponse(updatedRepository);
    }

    /**
     * {@inheritDoc}
     *
     * @throws ResourceNotFoundException if no repository exists with the given ID.
     */
    @Override
    @Transactional
    public void deleteRepository(Long id) {
        if (!repositoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Repository with ID " + id + " not found.");
        }
        repositoryRepository.deleteById(id);
    }

    /**
     * Helper method to map a {@link Repository} entity to a {@link RepositoryResponse} DTO.
     * @param repository The repository entity to map.
     * @return A {@link RepositoryResponse} DTO.
     */
    private RepositoryResponse mapRepositoryToRepositoryResponse(Repository repository) {
        return RepositoryResponse.builder()
                .id(repository.getId())
                .name(repository.getName())
                .description(repository.getDescription())
                .isPublic(repository.getIsPublic())
                .ownerId(repository.getOwner().getId()) // Map owner entity to ownerId DTO field
                .createdAt(repository.getCreatedAt())
                .updatedAt(repository.getUpdatedAt())
                .build();
    }
}