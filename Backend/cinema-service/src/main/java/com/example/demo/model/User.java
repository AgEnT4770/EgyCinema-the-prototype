package com.example.demo.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer id; // Ensure this is Integer for 'int identity'

    @Column(unique = true, nullable = false)
    private String username;

    // Try mapping explicitly to lowercase 'passwordhash' 
    // or simply 'password' if that's what is in your SQL table
    @Column(name = "password_hash", nullable = false) 
    private String passwordHash;

    @Column(nullable = false)
    private String salt;

    @Column(nullable = false)
    private String role;
}