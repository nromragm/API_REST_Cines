package com.es.diecines.service;

import com.es.diecines.dto.PeliculaDTO;
import com.es.diecines.model.Pelicula;
import com.es.diecines.model.Sesion;
import com.es.diecines.repository.PeliculaRepository;
import com.es.diecines.repository.SesionRepository;
import com.es.diecines.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private SesionRepository sesionRepository;

    public PeliculaDTO insert(PeliculaDTO peliculaDTO) {
        return Mapper.createPelicula(peliculaDTO, peliculaRepository);
    }

    public PeliculaDTO getById(String id) {

        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        Pelicula p = peliculaRepository.findById(idL).orElse(null);

        if (p == null) return null;

        return Mapper.mapToDTO(p);
    }

    public List<PeliculaDTO> getAll() {
        List<Pelicula> peliculas = peliculaRepository.findAll();

        List<PeliculaDTO> peliculaDTOS = new ArrayList<>();

        peliculas.forEach(pelicula -> peliculaDTOS.add(Mapper.mapToDTO(pelicula)));

        return peliculaDTOS;
    }

    public PeliculaDTO updateById(PeliculaDTO peliculaDTO, String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        Pelicula p = peliculaRepository.findById(idL).orElse(null);

        if (p == null) return null;

        p.setId(peliculaDTO.getId());
        p.setDirector(peliculaDTO.getDirector());
        p.setTime(peliculaDTO.getTime());
        p.setRating(peliculaDTO.getRating());
        p.setScreenshot(peliculaDTO.getScreenshot());
        p.setTitle(peliculaDTO.getTitle());
        p.setSynopsis(peliculaDTO.getSynopsis());
        p.setTrailer(peliculaDTO.getTrailer());
        p.setPosterImage(peliculaDTO.getPosterImage());

        peliculaRepository.save(p);

        return Mapper.mapToDTO(p);
    }

    public PeliculaDTO deleteById(String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        Pelicula p = peliculaRepository.findById(idL).orElse(null);

        if (p == null) return null;

        List<Sesion> sesiones = sesionRepository.findByPelicula(p);
        sesionRepository.deleteAll(sesiones);

        peliculaRepository.delete(p);

        return Mapper.mapToDTO(p);
    }

    public List<PeliculaDTO> getByMinRating(String rating) {
        Double ratingD;
        try {
            ratingD = Double.parseDouble(rating);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        List<Pelicula> peliculas = peliculaRepository.findAll();
        List<PeliculaDTO> peliculaDTOS = new ArrayList<>();

        peliculas.forEach(pelicula -> {
            if (pelicula.getRating() > ratingD) {
                peliculaDTOS.add(Mapper.mapToDTO(pelicula));
            }
        });

        return peliculaDTOS;
    }
}
