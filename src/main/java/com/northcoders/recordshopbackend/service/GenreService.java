package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.GenreDTO;
import com.northcoders.recordshopbackend.dto.GenreWithAlbumsDTO;
import com.northcoders.recordshopbackend.model.Genre;

import java.util.List;

public interface GenreService {
    List<GenreDTO> getAllGenres();
    Genre addNewGenre(String genre);
    Genre getOrCreateGenre(String genre);
    GenreWithAlbumsDTO getGenreByIdWithAlbums(Long genreId);
}
