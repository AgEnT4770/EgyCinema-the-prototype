package com.example.demo.service;

import com.example.demo.model.Cinema;
import com.example.demo.model.Hall;
import com.example.demo.repository.CinemaRepository;
import com.example.demo.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepo;

    @Autowired
    private HallRepository hallRepo;

    public List<Cinema> getAllCinemas() {
        return cinemaRepo.findAll();
    }

    public Cinema saveCinema(Cinema cinema) {
        return cinemaRepo.save(cinema);
    }

    // New: Fetch a single cinema by ID
    public Cinema getCinemaById(Long id) {
        return cinemaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cinema not found with id: " + id));
    }

    // New: Delete a cinema and its associated halls
    public void deleteCinema(Long id) {
        if (!cinemaRepo.existsById(id)) {
            throw new RuntimeException("Cannot delete. Cinema not found with id: " + id);
        }
        cinemaRepo.deleteById(id);
    }

    public Hall addHallToCinema(Long cinemaId, Hall hall) {
        Cinema cinema = cinemaRepo.findById(cinemaId)
                .orElseThrow(() -> new RuntimeException("Cinema not found"));
        hall.setCinema(cinema);
        return hallRepo.save(hall);
    }
}