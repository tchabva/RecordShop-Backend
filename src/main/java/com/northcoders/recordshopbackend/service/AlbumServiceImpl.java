package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.AlbumDTO;
import com.northcoders.recordshopbackend.model.Album;
import com.northcoders.recordshopbackend.repository.AlbumRepository;
import com.northcoders.recordshopbackend.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);

        return albums;
    }

    @Override
    public List<AlbumDTO> createListOfAlbumDTOs(List<Album> albums) {
        return albums
                .stream()
                .map(this::createAlbumDTO)
                .toList();
    }

    @Override
    public List<AlbumDTO> getAllInStockAlbumDTOs(List<AlbumDTO> albumDTOs) {
        return albumDTOs
                .stream()
                .filter(albumDTO -> albumDTO.getStock() > 0)
                .toList();
    }


    @Override
    public Album getAlbumById(Long albumId) {

        if (albumRepository.findById(albumId).isPresent()){
            return albumRepository.findById(albumId).get();
        } else{
            throw new ItemNotFoundException(String.format("Album with the id '%s' cannot be found", albumId)
            );
        }
    }

    // Album to DTO mapper
    @Override
    public AlbumDTO createAlbumDTO(Album album) {

        return AlbumDTO.builder()
                .id(album.getId())
                .title(album.getTitle())
                .artist(album.getArtist().getArtistName())
                .genre(album.getGenre())
                .releaseDate(album.getReleaseDate())
                .stock(album.getStock().getQuantityInStock())
                .build();
    }

    @Override
    public Album addNewAlbum(Album album) {
        return null;
    }

    @Override
    public Album updateAlbumStockById(Long albumId) {
        return null;
    }

    @Override
    public String deleteAlbumById(Long albumId) {
        return "";
    }
}
