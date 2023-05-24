package com.bridgeInvest.userservice.service.impl;


import com.bridgeInvest.userservice.model.entity.User;
import com.bridgeInvest.userservice.respository.UserRepository;
import com.bridgeInvest.userservice.service.UserInfoUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Custom UserDetailsService implementation that retrieves user information from UserRepository.
 */
@Component
public class UserInfoUserDetailsService implements UserDetailsService {

//   @Autowired
   private UserRepository repository;

    public UserInfoUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }
//    public UserInfoUserDetailsService()
//    {
//    }
    /**
     * Loads user details by username.
     *
     * @param username The username of the user.
     * @return The UserDetails object representing the user details.
     * @throws UsernameNotFoundException If user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
