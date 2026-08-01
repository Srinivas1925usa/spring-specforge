package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing business logic related to Users.
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves the total number of user records in the database.
     * This method is transactional and read-only for efficiency.
     *
     * @return The total count of users.
     * @throws IllegalStateException if an unexpected error occurs during database interaction.
     */
    @Transactional(readOnly = true)
    public long getTotalUserCount() {
        log.debug("Attempting to retrieve total user count from repository.");
        try {
            long count = userRepository.count();
            log.debug("Total user count retrieved: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Failed to retrieve total user count due to database error: {}", e.getMessage(), e);
            // Translate database exceptions into a more general service-layer exception
            throw new IllegalStateException("Could not retrieve total user count.", e);
        }
    }

    // Existing user-related service methods would also be here (e.g., saveUser, findUserById, etc.)
}