package com.example.demo.security;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtAuth {
    private static final long EXPIRATION_TIME = 86400000; // 24 hours
    private final SecretKey key = Jwts.SIG.HS256.key().build(); 

    public String generateToken(String username, String role) {
    String formattedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;

    return Jwts.builder()
            .subject(username)
            .claim("role", formattedRole) // Singular 'role'
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key)
            .compact();
}
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Inside JwtAuth.java
public String extractRole(String token) {
    Claims claims = extractClaims(token);
    // Look for singular 'role' first, then plural 'roles' as a backup
    String role = claims.get("role", String.class);
    if (role == null) {
        role = claims.get("role", String.class);
    }
    return role;
}

    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}