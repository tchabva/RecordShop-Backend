package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();
    Genre addNewGenre(String genre);
    Genre getOrCreateGenre(String genre);
}
