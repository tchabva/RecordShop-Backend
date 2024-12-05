package com.northcoders.recordshopbackend.model.dto;

import lombok.Data;

// The data transfer object for creating new albumn entries
public record PostAlbumDTO(String title, String artist, String releaseDate, int stock) {}
