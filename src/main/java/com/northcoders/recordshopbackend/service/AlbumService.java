package com.northcoders.recordshopbackend.service;


import com.northcoders.recordshopbackend.dto.AlbumDTO;
import com.northcoders.recordshopbackend.dto.StockDTO;
import com.northcoders.recordshopbackend.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    List<AlbumDTO> createListOfAlbumDTOs(List<Album> albums);
    List<AlbumDTO> getAllInStockAlbumDTOs();
    Album getAlbumById(Long albumId);
    AlbumDTO returnAlbumDTOById(Long albumId);
    AlbumDTO createAlbumDTO (Album album);
    Album addNewAlbum(AlbumDTO albumDTO);
    Album updateAlbumStockById(Long albumId, StockDTO stockDTO);
    String deleteAlbumById(Long albumId);
    String decreaseStockByAlbumId(Long albumId);
    AlbumDTO postNewAlbum(AlbumDTO albumDTO);
}