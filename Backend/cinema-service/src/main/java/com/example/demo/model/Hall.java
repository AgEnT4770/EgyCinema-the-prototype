package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Halls") // 1. Map to the plural table name
@Data
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HallID") // 2. Map to the specific PK column name
    private Integer id;      // 3. Change Long to Integer (matches SQL 'int')

    @Column(name = "Name")
    private String name;

    @Column(name = "TotalSeats") // Ensure this matches your DB column exactly
    private int totalSeats;

    @ManyToOne
    @JoinColumn(name = "CinemaID") // 4. Map to the FK column name in the Halls table
    @JsonIgnore 
    private Cinema cinema;
}