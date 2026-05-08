package com.example.booking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID")
    private Long id;

    @Column(name = "UserID", nullable = false)
    private Long userId;

    @Column(name = "ShowID", nullable = false)
    private Long showtimeId;

    @Column(name = "SeatsCount", nullable = false)
    private int seats;

    @Column(name = "BookingDate", nullable = false)
    private LocalDateTime bookingDate;

    public Booking() {}

    // 🔹 Auto set date لو مش متبعت
    @PrePersist
    public void prePersist() {
        if (bookingDate == null) {
            bookingDate = LocalDateTime.now();
        }
    }

    // 🔹 Getters & Setters (كاملين)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getShowtimeId() { return showtimeId; }
    public void setShowtimeId(Long showtimeId) { this.showtimeId = showtimeId; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
}