package com.example.demo.model;

import com.example.demo.model.Cinema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int totalSeats;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    @JsonIgnore // Important: Prevents infinite loops in JSON
    private Cinema cinema;
}