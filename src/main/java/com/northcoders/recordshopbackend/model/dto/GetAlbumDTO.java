package com.northcoders.recordshopbackend.model.dto;

import com.northcoders.recordshopbackend.model.enums.Genre;

// The AlbumDTO for when GetMapping is requested.
public record GetAlbumDTO(Long id, String title, String artist, Genre genre, String releaseDate, int stock) {}
