package com.example.demo.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter { 

    private final JwtAuth jwtAuth; //Service for handling JWT token validation and extraction of claims

    public JwtFilter(JwtAuth jwtAuth) {
        this.jwtAuth = jwtAuth; // Injects the JwtAuth for handling JWT token validation and extraction of claims
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) // Overrides the method to perform filtering logic for each incoming HTTP request
            throws ServletException, IOException {

        String header = request.getHeader("Authorization"); // Retrieves the "Authorization" header from the incoming HTTP request

        if (header != null && header.startsWith("Bearer ")) { // Checks if the header is present and starts with "Bearer "
            String token = header.substring(7);  // Extracts the JWT token from the header by removing the "Bearer " prefix

            if (jwtAuth.isTokenValid(token)) { // Validates the extracted JWT token using the JwtAuth service
                String username = jwtAuth.extractUsername(token);
                String role = jwtAuth.extractRole(token);
                UsernamePasswordAuthenticationToken auth = // Creates an authentication object with the extracted username and role from the JWT token
                    new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        List.of(new SimpleGrantedAuthority(role))
                    );

                SecurityContextHolder.getContext().setAuthentication(auth); // Sets the created authentication object in the SecurityContext, allowing Spring Security to recognize the user as authenticated for the current request
            }
        }

        chain.doFilter(request, response); // Continues the filter chain to allow the request to proceed to the next filter or the target resource
    }
}