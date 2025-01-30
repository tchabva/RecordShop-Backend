package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.AlbumDTO;
import com.northcoders.recordshopbackend.model.Album;
import org.jetbrains.annotations.NotNull;

// DTOMapper Interface
public interface DTOMapper {
     default AlbumDTO createAlbumDTO(@NotNull Album album) {
        return AlbumDTO.builder()
                .id(album.getId())
                .title(album.getTitle())
                .artist(album.getArtist().getArtistName())
                .genre(album.getGenre().getGenre())
                .releaseDate(String.valueOf(album.getReleaseDate()))
                .stock(album.getStock().getQuantityInStock())
                .price(album.getPrice())
                .artworkUrl(album.getArtworkUrl())
                .dateCreated(album.getDateCreated().toString())
                .dateModified(album.getDateModified().toString())
                .build();
    }
}
