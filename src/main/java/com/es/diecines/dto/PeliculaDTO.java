package com.es.diecines.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PeliculaDTO {
    private Long id;
    private String title;
    private String director;
    private String time;
    private String trailer;
    private String posterImage;
    private String screenshot;
    private String synopsis;
    private Double rating;

    public PeliculaDTO() {}

}
