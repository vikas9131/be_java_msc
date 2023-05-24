package com.bridgeInvest.userservice.service;



import com.bridgeInvest.userservice.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing users.
 */
public interface UserService {


    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    public List<User> getallUser();
    /**
     * Creates a new user.
     *
     * @param user The user object to be created.
     * @return The created user.
     */
    public User createUser(User user);
    /**
     * Retrieves a user by their name.
     *
     * @param name The name of the user.
     * @return An optional containing the user if found, or an empty optional if not found.
     */
    public Optional<User> getUserByName(String name);
}
