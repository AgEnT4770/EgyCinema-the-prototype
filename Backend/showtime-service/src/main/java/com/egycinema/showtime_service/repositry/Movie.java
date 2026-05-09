package com.egycinema.showtime_service.repositry;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "MovieID")
private Long movieId; // Changed to lowercase 'm'

@Column(name = "Title")
private String title;

@Column(name = "ImgURL")
private String imgUrl;

@Column(name = "Duration")
private Integer duration;

@Column(name = "ReleaseDate")
private String releaseDate; // Changed to lowercase 'r'

@Column(name = "CategoryID")
private Long categoryId;

@Column(name = "TrailerURL")
private String trailerUrl;

@Column(name = "Status")
private String status;

}