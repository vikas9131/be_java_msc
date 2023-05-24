package com.bridgeInvest.userservice.service;


import com.bridgeInvest.userservice.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
/**
 * Implementation of UserDetails representing the user details retrieved from the User entity.
 */
public class UserInfoUserDetails implements UserDetails {


    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    /**
     * Constructs a new UserInfoUserDetails instance based on the provided User object.
     *
     * @param userInfo The User object containing the user information.
     */
    public UserInfoUserDetails(User userInfo) {
        name=userInfo.getName();
        password=userInfo.getpassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
