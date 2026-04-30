package com.egycinema.showtime_service.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.List;

@Repository
public interface ShowTimeRepo extends JpaRepository<ShowTimeEntity,Long> {
    List<ShowTimeEntity> findByMovieId(Long movieId);
    List<ShowTimeEntity> findByCinemaId(Long cinemaId);
    List<ShowTimeEntity> findByMovieIdAndCinemaId(Long movieId, Long cinemaId);
    boolean existsByHallIdAndDateTime(Long hallId, LocalDateTime dateTime);


}
