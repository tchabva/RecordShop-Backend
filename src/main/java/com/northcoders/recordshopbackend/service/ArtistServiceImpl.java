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
        return new ArrayList<>(artistRepository.findAll());
    }

    @Override
    public Artist addNewArtist(String artistName) {
        return artistRepository.save(
                Artist.builder()
                        .artistName(artistName)
                        .build()
        );
    }

    @Override
    public Artist getOrCreateAlbumArtist(String artistName) {
        List<Artist> artists = getAllArtists();
        for (Artist artist : artists){
            if (artist.getArtistName().equals(artistName)){
                return artist;
            }
        }
        return addNewArtist(artistName);
    }
}
