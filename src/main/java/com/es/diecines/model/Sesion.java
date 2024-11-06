package com.es.diecines.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "sesiones")
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Pelicula pelicula;

    @Column(name = "room_id")
    private Long roomId;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    public Sesion() {}

    public Sesion(Pelicula pelicula, Long roomId, LocalDate date) {
        this.pelicula = pelicula;
        this.roomId = roomId;
        this.date = date;
    }


}