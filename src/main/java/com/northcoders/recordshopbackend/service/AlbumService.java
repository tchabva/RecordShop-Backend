package com.northcoders.recordshopbackend.service;


import com.northcoders.recordshopbackend.dto.AlbumDTO;
import com.northcoders.recordshopbackend.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    List<AlbumDTO> getAllInStockAlbums(List<Album> albums);
    Album getAlbumById(Long albumId);
    AlbumDTO createAlbumDTO (Album album);
    Album addNewAlbum(Album album);
    Album updateAlbumStockById(Long albumId);
    String deleteAlbumById(Long albumId);
}
