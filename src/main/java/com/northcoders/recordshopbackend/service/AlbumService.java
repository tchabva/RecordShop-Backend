package com.northcoders.recordshopbackend.service;


import com.northcoders.recordshopbackend.model.Album;
import com.northcoders.recordshopbackend.model.dto.GetAlbumDTO;
import com.northcoders.recordshopbackend.model.dto.PostAlbumDTO;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    Album getAlbumById(Long albumId);
    GetAlbumDTO createAlbumDTO (Album album);
    Album createAlbum(PostAlbumDTO postAlbumDTO);
    Album addNewAlbum(Album album);
    Album updateAlbumStockById(Long albumId);
    String deleteAlbumById(Long albumId);
}
