package com.bridgeInvest.userservice.config;



import com.bridgeInvest.userservice.service.impl.UserInfoUserDetailsService;
import com.bridgeInvest.userservice.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/**
 * JwtAuthenticationFilter is responsible for performing authentication and authorization filtering for incoming requests.
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserInfoUserDetailsService userInfoUserDetailsService;

    /**
     * parameterized Constructor of JwtAuthenticationFilter class
     * @param jwtUtils
     * @param userInfoUserDetailsService
     */
    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserInfoUserDetailsService userInfoUserDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userInfoUserDetailsService = userInfoUserDetailsService;
    }

    /**
     * Perform authentication and authorization filtering for incoming requests.
     *
     * @param request        The HTTP servlet request.
     * @param response       The HTTP servlet response.
     * @param filterChain    The filter chain for invoking subsequent filters.
     * @throws ServletException If an error occurs during the filter execution.
     * @throws IOException      If an I/O error occurs during the filter execution.
     */


	@SuppressWarnings("unused")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// TODO Auto-generated method stub

        String requestTokenHeader=request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
        {
            jwtToken =requestTokenHeader.substring(7);
            System.out.println(jwtToken);
            username= jwtUtils.extractUsername(jwtToken);


        }
        else {
            logger.info("Invalid Token, not start with Bearer String..!!");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            final UserDetails userDetails = this.userInfoUserDetailsService.loadUserByUsername(username);
            if(this.jwtUtils.validateToken(jwtToken,userDetails)) {
                // Token is valid.
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else{
                logger.info("Token is not valid...!!");
            }
        }
        filterChain.doFilter(request, response);
	}
}