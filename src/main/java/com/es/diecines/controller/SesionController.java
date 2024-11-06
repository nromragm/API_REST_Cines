package com.es.diecines.controller;

import com.es.diecines.dto.PeliculaDTO;
import com.es.diecines.dto.SesionDTO;
import com.es.diecines.repository.SesionRepository;
import com.es.diecines.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sesiones")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @PostMapping("/")
    public SesionDTO insert(
            @RequestBody SesionDTO sesionDTO
    ) {
        if (sesionDTO == null) return null;

        return sesionService.insert(sesionDTO);
    }

    @GetMapping("/")
    public List<SesionDTO> getAll() {
        return sesionService.getAll();
    }

    @DeleteMapping("/{id}")
    public SesionDTO deleteById(
            @PathVariable String id
    ) {
        if (id == null || id.isBlank()) return null;

        return sesionService.deleteById(id);
    }

    @PutMapping("/{id}")
    public SesionDTO updateById(
            @RequestBody SesionDTO sesionDTO,
            @PathVariable String id
    ) {
        if (id == null || id.isBlank() || sesionDTO == null) return null;

        return sesionService.updateById(sesionDTO, id);
    }

    @GetMapping("/hoy")
    public List<SesionDTO> getAllByDay() {
        return sesionService.getAllByDay();
    }
}
