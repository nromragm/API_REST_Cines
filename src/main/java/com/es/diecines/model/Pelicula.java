package com.es.diecines.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "peliculas")
public class Pelicula {

    // Getters y setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String director;

    private String time;

    private String trailer;

    @Column(name = "poster_image")
    private String posterImage;

    private String screenshot;

    @Column(length = 1000)
    private String synopsis;

    @Column(nullable = false)
    private Double rating;

    public Pelicula() {}

}