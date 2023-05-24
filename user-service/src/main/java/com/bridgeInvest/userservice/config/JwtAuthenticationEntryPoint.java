package com.bridgeInvest.userservice.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JwtAuthenticationEntryPoint is responsible for handling authentication exceptions when an unauthenticated user tries to access a protected resource.
 */
@Component
public class JwtAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    /**
     * Called when an unauthenticated user tries to access a protected resource.
     * This method is responsible for handling the authentication exception and sending an appropriate response.
     *
     * @param request        The HTTP servlet request.
     * @param response       The HTTP servlet response.
     * @param authException  The authentication exception that occurred.
     * @throws IOException      If an I/O error occurs while handling the exception.
     * @throws ServletException If an error occurs while handling the exception.
     */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

	}
}
