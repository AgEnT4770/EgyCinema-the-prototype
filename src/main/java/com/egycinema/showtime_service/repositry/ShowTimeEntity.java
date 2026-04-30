package com.egycinema.showtime_service.repositry;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.*;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long cinemaId;
    @NotNull
    private Long movieId;

    @NotNull
    private Long hallId;

    @NotNull
    private LocalDateTime dateTime;
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;


    @Positive
    private Double price;

//    for booking seat
//    private List<Integer> SeatID;
//
//    private boolean isAvailable;
}
