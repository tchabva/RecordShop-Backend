package com.northcoders.recordshopbackend.service;


import com.northcoders.recordshopbackend.dto.AlbumDTO;
import com.northcoders.recordshopbackend.dto.NewAlbumDTO;
import com.northcoders.recordshopbackend.dto.StockDTO;
import com.northcoders.recordshopbackend.dto.UpdateAlbumDTO;
import com.northcoders.recordshopbackend.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    List<AlbumDTO> getAllInStockAlbumDTOs();
    Album getAlbumById(Long albumId);
    AlbumDTO updateAlbumById(Long albumId, UpdateAlbumDTO updateAlbumDTO);
    AlbumDTO returnAlbumDTOById(Long albumId);
    Album addNewAlbum(NewAlbumDTO newAlbumDTO);
    Album updateAlbumStockById(Long albumId, StockDTO stockDTO);
    String deleteAlbumById(Long albumId);
    String decreaseStockByAlbumId(Long albumId);
    AlbumDTO postNewAlbum(NewAlbumDTO newAlbumDTO);
    List<AlbumDTO> getArtistAlbumsById(Long artistId);
    List<AlbumDTO> getArtistAlbumsByName(String artistName);
}