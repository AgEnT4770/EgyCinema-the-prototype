package com.example.demo.controller;

import com.example.demo.model.Cinema;
import com.example.demo.model.Hall;
import com.example.demo.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
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
}