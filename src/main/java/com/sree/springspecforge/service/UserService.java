package com.sree.springspecforge.service;

import com.sree.springspecforge.dto.UserDTO;
import com.sree.springspecforge.exception.UserNotFoundException;
import com.sree.springspecforge.model.User;
import com.sree.springspecforge.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for managing user-related business logic.
 * It interacts with the UserRepository to fetch user data and
 * maps User entities to UserDTOs for API responses.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a UserService with the given UserRepository.
     * @param userRepository The repository for User entities.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Finds a user by their unique identifier.
     *
     * @param userId The unique identifier of the user.
     * @return A UserDTO containing the user's id, name, role, and email.
     * @throws UserNotFoundException if no user with the specified ID is found.
     */
    @Transactional(readOnly = true) // Optimize for read-only operations
    public UserDTO findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return mapToDTO(user);
    }

    /**
     * Maps a User entity to a UserDTO.
     * This method ensures that only allowed fields are exposed in the API response,
     * now including the user's email.
     *
     * @param user The User entity to map.
     * @return A UserDTO representation of the user.
     */
    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail()); // Map the email field to the DTO
        return dto;
    }
}
