package com.example.demo.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtAuth {
    private static final long EXPIRATION_TIME = 86400000; // Expires in 24 hours
    private final SecretKey key = Jwts.SIG.HS256.key().build(); // Generate a secure random key for signing JWTs
    
    public String generateToken(String username) {
        return Jwts.builder() 
                .subject(username) // Set the username as the subject of the token
                .claim("roles", "USER") // Add specific user role as claim in the token
                .issuedAt(new Date()) // Set the token issuance time
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration for the token
                .signWith(key) // Sign the token with the generated key
                .compact(); // Build the JWT and serialize
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key) // Set the signing key for validating token
                .build()
                .parseSignedClaims(token) // Parse and validate signature of token
                .getPayload(); // Extract the claims from the token
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject(); // Extract the username from the token claims
    }

    public String extractRole(String token) {
        return extractClaims(token).get("roles", String.class); // Extract the user role from the token claims
    }

    public boolean isTokenValid(String token) {
        try {
            extractClaims(token); // Attempt to extract claims to validate token
            return true; // If extraction is successful, token is valid
            
        } catch (Exception e) {
            return false; // If any exception occurs during extraction, token is invalid
        }
    }
}