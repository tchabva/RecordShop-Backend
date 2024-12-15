package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.ArtistDTO;
import com.northcoders.recordshopbackend.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> getAllArtists();
    Artist addNewArtist(String artistName);
    Artist getOrCreateAlbumArtist(String artistName);
    ArtistDTO createArtistDTO(Artist artist);
    List<ArtistDTO> getAllArtistsDTO();
    Boolean isArtistPresent(Long artistId);
    ArtistDTO getArtistByName(String artistName);
}
