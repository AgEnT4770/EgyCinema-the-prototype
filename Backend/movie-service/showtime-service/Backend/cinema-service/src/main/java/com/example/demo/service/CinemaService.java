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

    public Hall addHallToCinema(Long cinemaId, Hall hall) {
        Cinema cinema = cinemaRepo.findById(cinemaId)
                .orElseThrow(() -> new RuntimeException("Cinema not found"));
        hall.setCinema(cinema);
        return hallRepo.save(hall);
    }
}