package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "Cinemas")
@Data 
public class Cinema {
   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "CinemaID") 
private Integer id; 

    @Column(name = "Name") 
    private String name;

    @Column(name = "Location") 
    private String location;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Hall> halls;
}