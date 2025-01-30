package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.ArtistDTO;
import com.northcoders.recordshopbackend.dto.ArtistWithAlbumsDTO;
import com.northcoders.recordshopbackend.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> getAllArtists();
    Artist addNewArtist(String artistName);
    Artist getOrCreateAlbumArtist(String artistName);
    List<ArtistDTO> getAllArtistsDTO();
    Boolean isArtistPresent(Long artistId);
    ArtistWithAlbumsDTO getArtistByNameWithAlbums(String artistName);
    ArtistWithAlbumsDTO getArtistByIdWithAlbums(Long artistId);
}