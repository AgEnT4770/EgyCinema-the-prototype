package com.example.demo.controller;

import com.example.demo.model.Cinema;
import com.example.demo.model.Hall;
import com.example.demo.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Cinemas")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping
    public List<Cinema> getCinemas() {
        return cinemaService.getAllCinemas();
    }

    @PostMapping
    public Cinema createCinema(@RequestBody Cinema cinema) {
        return cinemaService.saveCinema(cinema);
    }

    @PostMapping("/{id}/halls")
    public Hall addHall(@PathVariable Long id, @RequestBody Hall hall) {
        return cinemaService.addHallToCinema(id, hall);
    }
    // Add these inside CinemaController.java

    @PutMapping("/{id}")
    public Cinema updateCinema(@PathVariable Long id, @RequestBody Cinema cinemaDetails) {
    Cinema cinema = cinemaService.getCinemaById(id); // Ensure this method exists in your Service
    cinema.setName(cinemaDetails.getName());
    cinema.setLocation(cinemaDetails.getLocation());
    return cinemaService.saveCinema(cinema);
}

    @DeleteMapping("/{id}")
    public void deleteCinema(@PathVariable Long id) {
    cinemaService.deleteCinema(id); // Ensure this method exists in your Service
}
}