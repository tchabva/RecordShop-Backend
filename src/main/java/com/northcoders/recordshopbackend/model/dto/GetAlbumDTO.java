package com.northcoders.recordshopbackend.model.dto;

import com.northcoders.recordshopbackend.model.enums.Genre;

public record GetAlbumDTO(Long id, String title, String artist, Genre genre, String releaseDate, int stock) {}
