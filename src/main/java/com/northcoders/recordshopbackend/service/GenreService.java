package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.GenreDTO;
import com.northcoders.recordshopbackend.dto.GenreWithAlbumsDTO;
import com.northcoders.recordshopbackend.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();
    List<GenreDTO> getAllGenresDTO();
    Genre addNewGenre(String genre);
    Genre getOrCreateGenre(String genre);
    GenreWithAlbumsDTO getGenreByIdWithAlbums(Long genreId);
    GenreWithAlbumsDTO getGenreByNameWithAlbums(String genre);
}
