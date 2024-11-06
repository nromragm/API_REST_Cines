package com.es.diecines.controller;

import com.es.diecines.dto.PeliculaDTO;
import com.es.diecines.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @PostMapping("/")
    public PeliculaDTO insert(
            @RequestBody PeliculaDTO peliculaDTO
    ) {
        if (peliculaDTO == null) return null;

        return peliculaService.insert(peliculaDTO);
    }

    @GetMapping("/")
    public List<PeliculaDTO> getAll() {
        return peliculaService.getAll();
    }

    @GetMapping("/{id}")
    public PeliculaDTO getById(
            @PathVariable String id
    ) {

        if (id == null || id.isBlank()) return null;

        return peliculaService.getById(id);
    }

    public PeliculaDTO updateById(
            @RequestBody PeliculaDTO peliculaDTO,
            @PathVariable String id
    ) {
        if (id == null || id.isBlank() || peliculaDTO == null) return null;

        return peliculaService.updateById(peliculaDTO, id);
    }

    @DeleteMapping("/{id}")
    public PeliculaDTO deleteByID(
            @PathVariable String id
    ) {
        if (id == null || id.isBlank()) return null;

        return peliculaService.deleteById(id);
    }

    @GetMapping("/rating/{minRating}")
    public List<PeliculaDTO> getByMinRating(
            @PathVariable String rating
    ) {
        if (rating == null || rating.isBlank()) return null;

        return peliculaService.getByMinRating(rating);
    }
}
