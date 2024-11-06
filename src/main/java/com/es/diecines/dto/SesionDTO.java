package com.es.diecines.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SesionDTO {
    private Long id;
    private Long movieId;
    private Long roomId;
    private LocalDate date;

    public SesionDTO() {}

}
