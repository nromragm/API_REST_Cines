package com.es.diecines.service;

import com.es.diecines.dto.PeliculaDTO;
import com.es.diecines.dto.SesionDTO;
import com.es.diecines.model.Pelicula;
import com.es.diecines.model.Sesion;
import com.es.diecines.repository.PeliculaRepository;
import com.es.diecines.repository.SesionRepository;
import com.es.diecines.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    public SesionDTO insert(SesionDTO sesionDTO) {
        return Mapper.createSesion(sesionDTO, sesionRepository, peliculaRepository);
    }

    private LocalDate localDate;

    public List<SesionDTO> getAll() {
        List<Sesion> sesiones = sesionRepository.findAll();

        List<SesionDTO> sesionesDTOS = new ArrayList<>();

        sesiones.forEach(pelicula -> sesionesDTOS.add(Mapper.mapToDTO(pelicula)));

        return sesionesDTOS;
    }

    public SesionDTO deleteById(String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        Sesion sesion = sesionRepository.findById(idL).orElse(null);

        if (sesion == null) return null;

        sesionRepository.delete(sesion);

        return Mapper.mapToDTO(sesion);
    }

    public SesionDTO updateById(SesionDTO sesionDTO, String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        Sesion sesion = sesionRepository.findById(idL).orElse(null);

        if (sesion == null) return null;

        sesion.setId(sesionDTO.getId());

        Pelicula pelicula = peliculaRepository.findById(sesionDTO.getMovieId()).orElse(null);
        sesion.setPelicula(pelicula);
        sesion.setDate(sesionDTO.getDate());
        sesion.setRoomId(sesion.getRoomId());

        sesionRepository.save(sesion);

        return Mapper.mapToDTO(sesion);
    }

    public List<SesionDTO> getAllByDay() {
        List<Sesion> sesiones = sesionRepository.findAll();

        List<SesionDTO> sesionesDTOS = new ArrayList<>();

        sesiones.forEach(sesion -> {
            if (Objects.equals(sesion.getDate(), LocalDate.now())) {
                sesionesDTOS.add(Mapper.mapToDTO(sesion));
            }
        });
        return sesionesDTOS;
    }
}
