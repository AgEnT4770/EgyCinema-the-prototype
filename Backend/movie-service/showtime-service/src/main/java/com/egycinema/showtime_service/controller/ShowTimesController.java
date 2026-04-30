package com.egycinema.showtime_service.controller;

import com.egycinema.showtime_service.Service.ShowTimeService;
import com.egycinema.showtime_service.repositry.ShowTimeEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/showtimes")
public class ShowTimesController {
    @Autowired
    private ShowTimeService service;
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @PostMapping
    public ShowTimeEntity create(@Valid @RequestBody  ShowTimeEntity showtime) {
        return service.createShowtime(showtime);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ShowTimeEntity update(@PathVariable Long id, @RequestBody ShowTimeEntity showtime) {
        return service.update(id, showtime);
    }
    @GetMapping
    public List<ShowTimeEntity> getAll() {

        return service.getAll();
    }

    @GetMapping("/movie/{movieId}")
    public List<ShowTimeEntity> getByMovie(@PathVariable Long movieId) {

        return service.getByMovie(movieId);
    }

    @GetMapping("/cinema/{cinemaId}")
    public List<ShowTimeEntity> getByCinema(@PathVariable Long cinemaId) {

        return service.getByCinema(cinemaId);
    }
    @GetMapping("/movie/{movieId}/cinema/{cinemaId}")
    public List<ShowTimeEntity> getByMovieAndCinema(
            @PathVariable Long movieId,
            @PathVariable Long cinemaId
    ) {
        return service.getByMovieAndCinema(movieId, cinemaId);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ShowTimeEntity> patchShowtime(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        return ResponseEntity.ok(service.patchShowtime(id, updates));
    }

}
