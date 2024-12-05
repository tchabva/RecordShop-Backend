package com.northcoders.recordshopbackend.service;


import com.northcoders.recordshopbackend.model.Album;
import com.northcoders.recordshopbackend.model.dto.GetAlbumDTO;
import com.northcoders.recordshopbackend.model.dto.PostAlbumDTO;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    List<GetAlbumDTO> getAllInStockAlbums(List<Album> albums);
    Album getAlbumById(Long albumId);
    GetAlbumDTO createAlbumDTO (Album album);
    Album convertDTOtoAlbum(PostAlbumDTO postAlbumDTO);
    Album addNewAlbum(Album album);
    Album updateAlbumStockById(Long albumId);
    String deleteAlbumById(Long albumId);
}
