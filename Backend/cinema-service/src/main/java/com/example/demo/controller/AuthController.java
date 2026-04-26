package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allows the frontend to access the API from any origin

public class AuthController {

    private final AuthService authService; // Service for handling authentication logic

    public AuthController(AuthService authService) {
        this.authService = authService; // Injects the AuthService for handling authentication logic
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String>body) {
        String username = body.get("username"); // Extracts the username from the request body
        String password = body.get("password"); // Extracts the password from the request body
        boolean success = authService.registerUser(username, password); // Calls the AuthService to register the user
        if (success) {
            return ResponseEntity.ok("User registered successfully"); // Returns a success response if registration is successful
        } else {
            return ResponseEntity.status(400).body("Username already exists"); // Returns an error response if the username is already taken
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String>body) {
        String username = body.get("username"); // Extracts the username from the request body
        String password = body.get("password"); // Extracts the password from the request body
        String token = authService.loginUser(username, password); // Calls the AuthService to log in the user
        if (token != null) {
            return ResponseEntity.ok(token); // Returns a success response if login is successful
        } else {
            return ResponseEntity.status(401).body("Invalid credentials"); // Returns an error response if the credentials are invalid
        }
    }
}
