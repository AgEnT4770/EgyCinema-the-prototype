package com.egycinema.showtime_service.controller;

import com.egycinema.showtime_service.Service.MovieService;
import com.egycinema.showtime_service.Service.S3Service;
import com.egycinema.showtime_service.repositry.Movie;
import com.egycinema.showtime_service.repositry.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movies")
public class UploadController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepo;

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        System.out.println("STATUS VALUE = [" + movie.getStatus() + "]");

        return movieRepo.save(movie);
    }


    @PostMapping("/{movieId}/upload-poster")
    public ResponseEntity<?> upload(
            @PathVariable Long movieId,
            @RequestParam MultipartFile file) throws IOException {

        return ResponseEntity.ok(movieService.uploadPoster(file,movieId ));
    }
    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable Long movieId) {
        return movieService.getMovie(movieId);
    }
    @GetMapping
    public List<Movie> getAllMovies() {
        // For testing: manually return a list if database is empty
        return movieService.getAllMovies(); 
    }
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(
            @PathVariable Long id,
            @RequestBody Movie movie) {

        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Movie> patchMovie(
            @PathVariable Long id,
            @RequestBody Movie movie) {

        return ResponseEntity.ok(movieService.patchMovie(id, movie));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {

        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }

}