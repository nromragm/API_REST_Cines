package com.es.diecines.utils;

import com.es.diecines.dto.PeliculaDTO;
import com.es.diecines.dto.SesionDTO;
import com.es.diecines.model.Pelicula;
import com.es.diecines.model.Sesion;
import com.es.diecines.repository.PeliculaRepository;
import com.es.diecines.repository.SesionRepository;

public class Mapper {


    public static PeliculaDTO createPelicula(PeliculaDTO peliculaDTO, PeliculaRepository peliculaRepository) {

        Pelicula pelicula = new Pelicula();
        pelicula.setTitle(peliculaDTO.getTitle());
        pelicula.setDirector(peliculaDTO.getDirector());
        pelicula.setTime(peliculaDTO.getTime());
        pelicula.setTrailer(peliculaDTO.getTrailer());
        pelicula.setPosterImage(peliculaDTO.getPosterImage());
        pelicula.setScreenshot(peliculaDTO.getScreenshot());
        pelicula.setSynopsis(peliculaDTO.getSynopsis());
        pelicula.setRating(peliculaDTO.getRating());
        pelicula = peliculaRepository.save(pelicula);

        return mapToDTO(pelicula);
    }

    public static PeliculaDTO mapToDTO(Pelicula pelicula) {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setId(pelicula.getId());
        peliculaDTO.setTitle(pelicula.getTitle());
        peliculaDTO.setDirector(pelicula.getDirector());
        peliculaDTO.setTime(pelicula.getTime());
        peliculaDTO.setTrailer(pelicula.getTrailer());
        peliculaDTO.setPosterImage(pelicula.getPosterImage());
        peliculaDTO.setScreenshot(pelicula.getScreenshot());
        peliculaDTO.setSynopsis(pelicula.getSynopsis());
        peliculaDTO.setRating(pelicula.getRating());
        return peliculaDTO;
    }

    public static SesionDTO createSesion(SesionDTO sesionDTO, SesionRepository sesionRepository, PeliculaRepository peliculaRepository) {

        // Buscar la película en el repositorio a partir de movieId en el DTO
        Pelicula pelicula = peliculaRepository.findById(sesionDTO.getMovieId()).orElse(null);

        if (pelicula == null) return null;

        // Crear la sesión y asignar sus valores
        Sesion sesion = new Sesion();
        sesion.setPelicula(pelicula);
        sesion.setRoomId(sesionDTO.getRoomId());
        sesion.setDate(sesionDTO.getDate());

        // Guardar la sesión en el repositorio
        sesion = sesionRepository.save(sesion);

        return mapToDTO(sesion);
    }

    public static SesionDTO mapToDTO(Sesion sesion) {
        SesionDTO sesionDTO = new SesionDTO();
        sesionDTO.setId(sesion.getId());
        sesionDTO.setMovieId(sesion.getPelicula().getId());  // Usar el ID de la película asociada
        sesionDTO.setRoomId(sesion.getRoomId());
        sesionDTO.setDate(sesion.getDate());
        return sesionDTO;
    }

}
