package com.egycinema.showtime_service.Service;

import com.egycinema.showtime_service.expcition.CustomException;
import com.egycinema.showtime_service.repositry.Movie;
import com.egycinema.showtime_service.repositry.MovieRepository;
import com.egycinema.showtime_service.repositry.ShowTimeEntity;
import com.egycinema.showtime_service.repositry.ShowTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Map;

@Service
public class ShowTimeService {
    @Autowired
    private ShowTimeRepo repo;
    @Autowired
    private MovieRepository movieRepo;
    public ShowTimeEntity createShowtime(ShowTimeEntity showtime ) {
        if (showtime.getDateTime().isBefore(LocalDateTime.now())) {
            throw new CustomException("Cannot create showtime in the past");
        }
        showtime.setDate(showtime.getDateTime().toLocalDate());
        showtime.setTime(showtime.getDateTime().toLocalTime());
        if (showtime.getMovie() == null || showtime.getMovie().getMovieID() == null) {
            throw new CustomException("Movie is required");
        }

        Long movieId = showtime.getMovie().getMovieID();

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        showtime.setMovie(movie);



        // RULE 2: prevent duplicate
        boolean exists = repo.existsByHallIdAndDateTime(
                showtime.getHallId(),
                showtime.getDateTime()
        );
        if (exists) {
            throw new CustomException("Hall already has a showtime at this time");
        }
        return repo.save(showtime);
    }
    public List<ShowTimeEntity> getAll() {
        List<ShowTimeEntity> list = repo.findAll();

        // force initialize movie before JSON serialization
        list.forEach(st -> {
            if (st.getMovie() != null) {
                st.getMovie().getMovieID(); // forces load
            }
        });

        return list;
    }

    public List<ShowTimeEntity> getByMovie(Long movieId) {

        return repo.findByMovie_MovieID(movieId);
    }

    public ShowTimeEntity update(Long id, ShowTimeEntity newData) {
        ShowTimeEntity existing = repo.findById(id)
                .orElseThrow(() ->  new CustomException("Showtime not found"));

        existing.setDateTime(newData.getDateTime());

        existing.setPrice(newData.getPrice());

        return repo.save(existing);
    }
    public void delete(Long id) {
        ShowTimeEntity existing = repo.findById(id)
                .orElseThrow(() -> new CustomException("Showtime not found"));

        repo.delete(existing);
    }
    public List<ShowTimeEntity> getByCinema(Long cinemaId) {

        return repo.findByCinemaId(cinemaId);
    }
    public List<ShowTimeEntity> getByMovieAndCinema(Long movieId, Long cinemaId) {
        return repo.findByMovie_MovieIDAndCinemaId(movieId, cinemaId);
    }



    public ShowTimeEntity patchShowtime(Long id, Map<String, Object> updates) {
        ShowTimeEntity existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        updates.forEach((key, value) -> {

            switch (key) {

                case "cinemaId":
                    existing.setCinemaId(Long.valueOf(value.toString()));
                    break;

                case "movieId":
                    Movie movie = movieRepo.findById(Long.valueOf(value.toString()))
                            .orElseThrow(() -> new RuntimeException("Movie not found"));

                    existing.setMovie(movie);
                    break;

                case "hallId":
                    existing.setHallId(Long.valueOf(value.toString()));
                    break;

                case "price":
                    existing.setPrice(Double.valueOf(value.toString()));
                    break;

                case "dateTime":
                    LocalDateTime dateTime = LocalDateTime.parse(value.toString());
                    existing.setDateTime(dateTime);

                    // keep DB columns synced (important in your case)
                    existing.setDate(dateTime.toLocalDate());
                    existing.setTime(dateTime.toLocalTime());
                    break;
            }
        });

        return repo.save(existing);
    }
}
