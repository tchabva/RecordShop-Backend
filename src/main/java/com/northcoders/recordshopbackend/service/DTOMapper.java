package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.*;
import com.northcoders.recordshopbackend.model.Album;
import com.northcoders.recordshopbackend.model.Artist;
import com.northcoders.recordshopbackend.model.Genre;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    default List<AlbumDTO> createListOfAlbumDTOs(List<Album> albums) {
        return albums
                .stream()
                .map(this::createAlbumDTO)
                .toList();
    }

    default ArtistWithAlbumsDTO createArtistWithAlbumsDTO(@NotNull Artist artist) {
        return ArtistWithAlbumsDTO.builder()
                .id(artist.getId())
                .artistName(artist.getArtistName())
                .albums(artist.getAlbums().stream().map(this::createAlbumDTO).toList())
                .build();
    }

    default ArtistDTO createArtistDTO(@NotNull Artist artist) {
        return ArtistDTO.builder()
                .id(artist.getId())
                .artistName(artist.getArtistName())
                .build();
    }

    default GenreDTO createGenreDTO(Genre genre){
         return GenreDTO.builder()
                 .id(genre.getId())
                 .genre(genre.getGenre())
                 .build();
    }

    default GenreWithAlbumsDTO createGenreWithDTO(Genre genre){
        return GenreWithAlbumsDTO.builder()
                .id(genre.getId())
                .genre(genre.getGenre())
                .albums(genre.getAlbums().stream().map(this::createAlbumDTO).toList())
                .build();
    }
}