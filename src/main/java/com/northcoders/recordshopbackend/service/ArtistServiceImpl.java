package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.ArtistDTO;
import com.northcoders.recordshopbackend.dto.ArtistWithAlbumsDTO;
import com.northcoders.recordshopbackend.exception.ItemNotFoundException;
import com.northcoders.recordshopbackend.model.Artist;
import com.northcoders.recordshopbackend.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService, DTOMapper {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>(artistRepository.findAll());
        return artists.stream()
                .sorted(Comparator.comparing(artist -> artist.getArtistName().toLowerCase()))
                .toList();
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
        for (Artist artist : artists) {
            if (artist.getArtistName().equals(artistName)) {
                return artist;
            }
        }
        return addNewArtist(artistName);
    }

    @Override
    public ArtistWithAlbumsDTO getArtistByNameWithAlbums(String artistName) {
        if (artistRepository.findByArtistName(artistName).isPresent()) {
            return createArtistWithAlbumsDTO(artistRepository.findByArtistName(artistName).get());
        } else {
            throw new ItemNotFoundException(String.format("Artist with the name '%s' cannot be found", artistName));
        }
    }

    @Override
    public List<ArtistDTO> getAllArtistsDTO() {
        List<Artist> artists = getAllArtists();
        return artists.stream()
                .filter(artist -> !artist.getAlbums().isEmpty())
                .map(this::createArtistDTO).toList();
    }

    @Override
    public ArtistWithAlbumsDTO getArtistByIdWithAlbums(Long artistId) {

        if (artistRepository.findById(artistId).isPresent()) {
            return createArtistWithAlbumsDTO(artistRepository.findById(artistId).get());
        } else {
            throw new ItemNotFoundException(String.format("Artist with the id '%d' cannot be found", artistId));
        }
    }
}