package com.bridgeInvest.userservice.controller;

import com.bridgeInvest.userservice.UserServiceApplication;
import com.bridgeInvest.userservice.model.dto.JwtRequest;
import com.bridgeInvest.userservice.model.dto.JwtResponse;
import com.bridgeInvest.userservice.service.impl.UserInfoUserDetailsService;
import com.bridgeInvest.userservice.utils.JwtUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling user authentication and generating JWT tokens.
 */
@RestController
@RequestMapping(value = "/api/V1/authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserInfoUserDetailsService userInfoUserDetailsService;
    private final JwtUtils jwtUtils;
 //   private static final Logger logger = LogManager.getLogger(AuthenticationController.class);
   // private static final Logger logger = (Logger) LogManager.getLogger(AuthenticationController.class);
   private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    /**
     * parameterized constructor of AuthenticationController class
     * @param authenticationManager
     * @param userInfoUserDetailsService
     * @param jwtUtils
     */
    public AuthenticationController(AuthenticationManager authenticationManager, UserInfoUserDetailsService userInfoUserDetailsService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userInfoUserDetailsService = userInfoUserDetailsService;
        this.jwtUtils = jwtUtils;
    }



    // generate-token
    /**
     * Generates a JWT token for the provided JWT request.
     *
     * @param jwtRequest The JWT request containing the username and password.
     * @return A ResponseEntity containing the generated JWT token.
     * @throws Exception If user authentication fails or the user is not found.
     */
     @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());


        }catch(Exception e) {
            throw new Exception("User not found ");
        }
        // authenticate
        UserDetails userDetails = this.userInfoUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
       logger.info("Token is not valid...!!");
        logger.debug("HIIIIIIIIIIII.........");
        return ResponseEntity.ok(new JwtResponse.Builder().settoken(token).build());
    }
    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @throws Exception If authentication fails due to disabled user or invalid credentials.
     */

    private void authenticate(String username, String password) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            // TODO: handle exception
            throw new Exception("USER DISABLES "+ e.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("Invalid Credentials "+ e.getMessage());
        }
    }

}
