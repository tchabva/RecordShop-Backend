package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.ArtistDTO;
import com.northcoders.recordshopbackend.exception.ItemNotFoundException;
import com.northcoders.recordshopbackend.model.Album;
import com.northcoders.recordshopbackend.model.Artist;
import com.northcoders.recordshopbackend.repository.ArtistRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public ArtistDTO createArtistDTO(Artist artist) {
        return ArtistDTO.builder()
                .id(artist.getId())
                .artistName(artist.getArtistName())
                .build();
    }

    @Override
    public List<ArtistDTO> getAllArtistsDTO() {
        List<Artist> artists = getAllArtists();
        return artists.stream().map(this::createArtistDTO).toList();
    }
}
