package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*") // Allows the frontend to access the admin API from any origin
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository; // Injects UserRepository for direct database operations on users
    }

    // View all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll(); // Retrieves all users from the database
        return ResponseEntity.ok(users); // Returns the list of users with a 200 OK status
    }

    // Change a user's role
    @PutMapping("/users/{username}/role")
    public ResponseEntity<String> updateRole(@PathVariable String username,
                                              @RequestBody Map<String, String> body) {
        return userRepository.findByUsername(username) // Finds the user by username
                .map(user -> {
                    user.setRole(body.get("role")); // Updates the user's role with the provided value
                    userRepository.save(user); // Saves the updated user back to the database
                    return ResponseEntity.ok("Role updated successfully for: " + username); // 200 OK
                })
                .orElse(ResponseEntity.status(404).body("User not found: " + username)); // 404 if user doesn't exist
    }

    // Delete a user
    @DeleteMapping("/users/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        return userRepository.findByUsername(username) // Finds the user by username
                .map(user -> {
                    userRepository.delete(user); // Deletes the user from the database
                    return ResponseEntity.ok("User deleted successfully: " + username); // 200 OK
                })
                .orElse(ResponseEntity.status(404).body("User not found: " + username)); // 404 if user doesn't exist
    }
}