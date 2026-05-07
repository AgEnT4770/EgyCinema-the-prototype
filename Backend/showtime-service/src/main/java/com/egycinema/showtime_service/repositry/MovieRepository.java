package com.egycinema.showtime_service.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAll();
    List<Movie> findByTitleContainingIgnoreCase(String title);
    boolean existsById(Long id);

}