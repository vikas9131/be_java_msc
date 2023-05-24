package com.bridgeInvest.userservice.service.impl;


import com.bridgeInvest.userservice.respository.UserRepository;
import com.bridgeInvest.userservice.model.entity.User;
import com.bridgeInvest.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the UserService interface.
 */
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

	/**
	 * Constructs a new UserServiceImpl instance.
	 *
	 * @param userRepository The UserRepository instance.
	 */
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Retrieves all users.
	 *
	 * @return A list of User objects representing all users.
	 */
	@Override
    public List<User> getallUser() {
        return userRepository.findAll();
    }

	/**
	 * Creates a new user.
	 *
	 * @param user The User object representing the user to be created.
	 * @return The created User object.
	 */
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	/**
	 * Retrieves a user by name.
	 *
	 * @param name The name of the user.
	 * @return An Optional containing the User object if found, or an empty Optional if not found.
	 */
	@Override
	public Optional<User> getUserByName(String name) {
		return userRepository.findByName(name);
	}
}
