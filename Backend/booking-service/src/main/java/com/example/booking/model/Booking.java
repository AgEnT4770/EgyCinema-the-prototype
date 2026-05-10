package com.example.booking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID")
    private Integer id;

   
    private Long userId;

    // ده فعليًا Showtime ID
    
    private Integer showtimeId;

    // Movie FK
    @Column(name = "MovieID", nullable = false)
    private Integer movieId;

    @Column(name = "SeatsCount", nullable = false)
    private int seats;

    @Column(name = "BookingDate", nullable = false)
    private LocalDateTime bookingDate;

    public Booking() {}

    @PrePersist
    public void prePersist() {

        if (bookingDate == null) {
            bookingDate = LocalDateTime.now();
        }
    }

    // =========================
    // Getters & Setters
    // =========================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Integer showtimeId) {
        this.showtimeId = showtimeId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}