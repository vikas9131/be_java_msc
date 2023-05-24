package com.bridgeInvest.userservice.controller;

import com.bridgeInvest.userservice.model.entity.User;
import com.bridgeInvest.userservice.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling user-related endpoints.
 */
@RestController
@RequestMapping("/api/V1/user")
public class UserController {
//    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * Constructs a new UserController with the provided UserService.
     *
     * @param userService The UserService instance.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list of User objects.
     */
    @GetMapping("/getall")
    public List<User> getall(){
//        LOGGER.info("UserController -> getall method called");
        return userService.getallUser();
    }

    /**
     * Create a new user.
     *
     * @param user The user object containing the user information.
     * @return ResponseEntity indicating the result of the operation.
     *         If the username already exists, a bad request response with an error message is returned.
     *         If the user is successfully registered, an OK response is returned.
     */
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody User user)
    {
        if (userService.getUserByName(user.getName()).isPresent()) {
//            LOGGER.info("Username already exists");
            return ResponseEntity.badRequest().body("Username already exists");
        }else {
            // Save the user
            userService.createUser(user);
//            LOGGER.info("User registered successfully");
            return ResponseEntity.ok("User registered successfully");
        }
    }
}
