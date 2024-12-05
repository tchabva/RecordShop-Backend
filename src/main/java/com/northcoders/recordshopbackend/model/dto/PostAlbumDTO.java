package com.northcoders.recordshopbackend.model.dto;

import com.northcoders.recordshopbackend.model.enums.Genre;

// The data transfer object for creating new albumn entries
public record PostAlbumDTO(String title, String artist, Genre genre, String releaseDate, int stock) {}
