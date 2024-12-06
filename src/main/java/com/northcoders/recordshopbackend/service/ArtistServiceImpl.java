package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.model.Artist;
import com.northcoders.recordshopbackend.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        artistRepository.findAll().forEach(artists::add);
        return artists;
    }

    @Override
    public Artist addNewArtist(String artistName) {
        return artistRepository.save(
                Artist.builder()
                        .artistName(artistName)
                        .build()
        );
    }
}
