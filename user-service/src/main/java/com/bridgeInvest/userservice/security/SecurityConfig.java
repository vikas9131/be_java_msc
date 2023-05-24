package com.bridgeInvest.userservice.security;

import com.bridgeInvest.userservice.config.JwtAuthenticationEntryPoint;
import com.bridgeInvest.userservice.config.JwtAuthenticationFilter;
import com.bridgeInvest.userservice.respository.UserRepository;
import com.bridgeInvest.userservice.service.impl.UserInfoUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/**
 * Configuration class for security settings.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    private final JwtAuthenticationFilter authFilter;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final UserRepository repository;

    /**
     * Constructs a new SecurityConfig.
     *
     * @param authFilter          The JwtAuthenticationFilter.
     * @param unauthorizedHandler The JwtAuthenticationEntryPoint.
     * @param repository
     */
    public SecurityConfig(JwtAuthenticationFilter authFilter, JwtAuthenticationEntryPoint unauthorizedHandler, UserRepository repository) {
        this.authFilter = authFilter;
        this.unauthorizedHandler = unauthorizedHandler;
        this.repository = repository;
    }

    /**
     * Retrieves the UserDetailsService bean.
     *
     * @return The UserDetailsService.
     */
    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService(repository);
    }
    /**
     * Configures the security filter chain.
     *
     * @param http The HttpSecurity instance.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/api/V1/authentication/generate-token"),
                        new AntPathRequestMatcher("/api/V1/user/hii"),
                       new AntPathRequestMatcher("/api/V1/user/create")
                ).permitAll()
                .anyRequest()     
                .authenticated()
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Retrieves the PasswordEncoder bean.
     *
     * @return The PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
   //new BCryptPasswordEncoder();
    }
    /**
     * Retrieves the AuthenticationProvider bean.
     *
     * @return The AuthenticationProvider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    /**
     * Retrieves the AuthenticationManager bean.
     *
     * @param config The AuthenticationConfiguration.
     * @return The AuthenticationManager.
     * @throws Exception If an error occurs while retrieving the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
