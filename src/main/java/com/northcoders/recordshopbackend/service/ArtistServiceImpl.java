package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.ArtistDTO;
import com.northcoders.recordshopbackend.exception.ItemNotFoundException;
import com.northcoders.recordshopbackend.model.Artist;
import com.northcoders.recordshopbackend.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService, DTOMapper{

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
                .albums(artist.getAlbums().stream().map(this::createAlbumDTO).toList())
                .build();
    }

    @Override
    public Boolean isArtistPresent(Long artistId) {
        return artistRepository.existsById(artistId);
    }

    @Override
    public ArtistDTO getArtistByName(String artistName) {
        if(artistRepository.findByArtistName(artistName).isPresent()){
            return createArtistDTO(artistRepository.findByArtistName(artistName).get());
        }else {
            throw new ItemNotFoundException(String.format("Artist with the name '%s' cannot be found", artistName));
        }
    }

    @Override
    public List<ArtistDTO> getAllArtistsDTO() {
        List<Artist> artists = getAllArtists();
        return artists.stream().map(this::createArtistDTO).toList();
    }

    @Override
    public ArtistDTO getArtistByIdWithAlbums(Long artistId) {

        if(artistRepository.findByIdWithAlbums(artistId).isPresent()){
            return createArtistDTO(artistRepository.findByIdWithAlbums(artistId).get());
        }else {
            throw new ItemNotFoundException(String.format("Artist with the id '%d' cannot be found", artistId));
        }
    }
}
