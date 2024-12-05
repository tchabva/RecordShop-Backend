package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.model.Album;

import java.util.List;

public class AlbumServiceImpl implements AlbumService{
    @Override
    public List<Album> getAllAlbums() {
        return List.of();
    }

    @Override
    public Album getAlbumById(Long albumId) {
        return null;
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
