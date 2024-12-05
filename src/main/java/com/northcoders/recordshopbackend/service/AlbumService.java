package com.northcoders.recordshopbackend.service;


import com.northcoders.recordshopbackend.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    Album getAlbumById(Long albumId);
    Album addNewAlbum(Album album);
    Album updateAlbumStockById(Long albumId);
    String deleteAlbumById(Long albumId);
}
