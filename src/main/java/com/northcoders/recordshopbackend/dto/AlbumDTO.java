package com.northcoders.recordshopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
// The AlbumDTO for when GetMapping is requested.
public class AlbumDTO{

        private Long id;
        private String title;
        private String artist;
        private String genre;
        private String releaseDate;
        private Integer stock;
        private Double price;
        private String dateCreated;
        private String dateModified;
}
