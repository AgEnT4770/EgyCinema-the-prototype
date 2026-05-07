package com.egycinema.showtime_service.Service;

import com.egycinema.showtime_service.repositry.Movie;
import com.egycinema.showtime_service.repositry.MovieRepository;
import com.egycinema.showtime_service.repositry.ShowTimeEntity;
import com.egycinema.showtime_service.repositry.ShowTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private ShowTimeRepo showRepo;

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private S3Service s3Service;

    public String uploadPoster(MultipartFile file, Long movieId) throws IOException {

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        String url = s3Service.uploadFile(file);

        movie.setImgUrl(url);
        movieRepo.save(movie);

        return url;
    }
    public Movie getMovie(Long movieId) {
        return movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }
    public Movie updateMovie(Long movieId, Movie updatedMovie) {

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // FULL REPLACEMENT (PUT behavior)
        movie.setTitle(updatedMovie.getTitle());
        movie.setDuration(updatedMovie.getDuration());
        movie.setReleaseDate(updatedMovie.getReleaseDate());
        movie.setImgUrl(updatedMovie.getImgUrl());
        movie.setTrailerUrl(updatedMovie.getTrailerUrl());
        movie.setStatus(updatedMovie.getStatus());
        movie.setCategoryId(updatedMovie.getCategoryId());

        return movieRepo.save(movie);
    }
    public Movie patchMovie(Long movieId, Movie updatedMovie) {

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // ONLY update non-null fields
        if (updatedMovie.getTitle() != null)
            movie.setTitle(updatedMovie.getTitle());

        if (updatedMovie.getDuration() != null)
            movie.setDuration(updatedMovie.getDuration());

        if (updatedMovie.getReleaseDate() != null)
            movie.setReleaseDate(updatedMovie.getReleaseDate());

        if (updatedMovie.getImgUrl() != null)
            movie.setImgUrl(updatedMovie.getImgUrl());

        if (updatedMovie.getTrailerUrl() != null)
            movie.setTrailerUrl(updatedMovie.getTrailerUrl());

        if (updatedMovie.getStatus() != null)
            movie.setStatus(updatedMovie.getStatus());

        if (updatedMovie.getCategoryId() != null)
            movie.setCategoryId(updatedMovie.getCategoryId());

        return movieRepo.save(movie);
    }
    public void deleteMovie(Long movieId) {

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movieRepo.delete(movie);
    }
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

}