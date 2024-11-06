package com.es.diecines.repository;

import com.es.diecines.model.Pelicula;
import com.es.diecines.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, Long> {
    List<Sesion> findByPelicula(Pelicula pelicula);
}
