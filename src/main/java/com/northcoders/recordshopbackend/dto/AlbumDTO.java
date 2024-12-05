package com.northcoders.recordshopbackend.dto;

import com.northcoders.recordshopbackend.model.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

// The AlbumDTO for when GetMapping is requested.
public class AlbumDTO{

        private Long id;
        private String title;
        private String artist;
        private Genre genre;
        private String releaseDate;
        private int stock;
}
