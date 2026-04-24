package com.example.demo.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto increments an ID for each new user
    private Long id;
    @Column(unique = true, nullable = false) //Username must be unique and not empty or null
    private String username;
    @Column(nullable = false) //Password must not be empty or null
    private String passwordHash; //Stores the hashed password from UserAuth
    @Column(nullable = false) //Salt must not be empty or null
    private byte[] salt; //Stores the salt used for hashing the password
    @Column(nullable = false) //Role must not be empty or null
    private String role; //Stores the user role 
    
}
