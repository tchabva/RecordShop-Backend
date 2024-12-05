package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.model.Album;
import com.northcoders.recordshopbackend.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);

        return albums;
    }

    @Override
    public Album getAlbumById(Long albumId) {

        if (albumRepository.findById(albumId).isPresent()){
            return albumRepository.findById(albumId).get();
        }

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
