package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.AlbumDTO;
import com.northcoders.recordshopbackend.dto.StockDTO;
import com.northcoders.recordshopbackend.model.Album;
import com.northcoders.recordshopbackend.repository.AlbumRepository;
import com.northcoders.recordshopbackend.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private StockService stockService;

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
    public Album addNewAlbum(AlbumDTO albumDTO) {
        return albumRepository.save(Album.builder()
                .title(albumDTO.getTitle())
                .artist(artistService.getOrCreateAlbumArtist(albumDTO.getArtist()))
                .genre(albumDTO.getGenre())
                .releaseDate(albumDTO.getReleaseDate())
                .stock(stockService.addNewStock(albumDTO.getStock()))
                .build());
    }

    @Override
    public Album updateAlbumStockById(Long albumId, StockDTO stockDTO) {
        return null;
    }

    @Override
    public String deleteAlbumById(Long albumId) {
        return "";
    }
}
