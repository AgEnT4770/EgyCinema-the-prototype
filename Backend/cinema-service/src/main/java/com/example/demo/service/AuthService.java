package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtAuth;
import com.example.demo.security.UserAuth;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service

public class AuthService {

    private final UserRepository userRepository; //Repository for performing database operations related to users
    private final JwtAuth jwtAuth; //Service for handling JWT token generation and validation

    public AuthService(UserRepository userRepository, JwtAuth jwtAuth) {
        this.userRepository = userRepository; // Injects the UserRepository for database operations related to users
        this.jwtAuth = jwtAuth; // Injects the JwtAuth for handling JWT token generation and validation
    }

    public boolean registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) { //Checks if the username already exists
            return false; // If username is taken, registration fails
        }
        byte[] salt = UserAuth.passSalt(); //Generates a random salt for hashing the password
        String hashedPassword = UserAuth.hashPassword(password, salt); //Hashes the password using the generated salt
        String encodedSalt = Base64.getEncoder().encodeToString(salt); //Encodes the salt to a string for storage in the database
        User newUser = new User(); //Creates a new user entity
        newUser.setUsername(username); //Sets the username for the new user
        newUser.setPasswordHash(hashedPassword); //Sets the hashed password for the new user
        newUser.setSalt(encodedSalt); //Sets the encoded salt for the new user
        newUser.setRole("ROLE_USER"); //Assigns a default role of "ROLE_USER" to the new user

        userRepository.save(newUser); //Saves the new user to the database
        return true;
    }

    public String loginUser(String username, String password) {
        return userRepository.findByUsername(username) //Finds the user by their username
                .filter(user -> UserAuth.verifyPassword(password, Base64.getDecoder().decode(user.getSalt()), user.getPasswordHash())) //Verifies the provided password against the stored hash and salt
                .map(user -> jwtAuth.generateToken(username)) //If authentication is successful, generates a JWT token for the user
                .orElse(null); //If authentication fails, returns null
    }
}
