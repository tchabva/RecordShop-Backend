package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.GenreDTO;
import com.northcoders.recordshopbackend.model.Genre;
import com.northcoders.recordshopbackend.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService, DTOMapper{

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream().map(this::createGenreDTO).toList();
    }

    @Override
    public Genre addNewGenre(String genre) {
        return genreRepository.save(
                Genre.builder()
                        .genre(genre)
                        .build()
        );
    }

    @Override
    public Genre getOrCreateGenre(String genre) {
        List<Genre> genres = genreRepository.findAll();
        for(Genre g : genres){
            if(g.getGenre().equalsIgnoreCase(genre)){
                return g;
            }
        }
        return addNewGenre(genre);
    }
}