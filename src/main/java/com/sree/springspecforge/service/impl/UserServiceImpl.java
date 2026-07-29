package com.sree.springspecforge.service.impl;

import com.sree.springspecforge.dto.CreateUserRequest;
import com.sree.springspecforge.dto.UserResponse;
import com.sree.springspecforge.entity.User;
import com.sree.springspecforge.exception.BadRequestException;
import com.sree.springspecforge.exception.ResourceNotFoundException;
import com.sree.springspecforge.repository.UserRepository;
import com.sree.springspecforge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link UserService} interface.
 * Handles business logic for user management, including validation and interaction with the {@link UserRepository}.
 */
@Service
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     *
     * Ensures username and email are unique before creating the user.
     * @throws BadRequestException if username or email already exists.
     */
    @Override
    @Transactional // Ensures the entire method executes within a single transaction
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username '" + request.getUsername() + "' already exists.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email '" + request.getEmail() + "' already exists.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        // createdAt and updatedAt are automatically handled by @EnableJpaAuditing

        User savedUser = userRepository.save(user);
        return mapUserToUserResponse(savedUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true) // Optimizes read operations
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapUserToUserResponse)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     *
     * @throws ResourceNotFoundException if no user exists with the given ID.
     */
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));
        return mapUserToUserResponse(user);
    }

    /**
     * Helper method to map a {@link User} entity to a {@link UserResponse} DTO.
     * @param user The user entity to map.
     * @return A {@link UserResponse} DTO.
     */
    private UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}