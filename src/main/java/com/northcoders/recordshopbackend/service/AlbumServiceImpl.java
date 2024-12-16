package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.*;
import com.northcoders.recordshopbackend.model.Album;
import com.northcoders.recordshopbackend.model.Artist;
import com.northcoders.recordshopbackend.model.Stock;
import com.northcoders.recordshopbackend.repository.AlbumRepository;
import com.northcoders.recordshopbackend.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    @Autowired
    private GenreService genreService;

    @Autowired
    private CacheService<Album> albumCacheService;

    @Override
    public List<Album> getAllAlbums() {
        return new ArrayList<>(albumRepository.findAll());
    }

    @Override
    public List<AlbumDTO> createListOfAlbumDTOs(List<Album> albums) {
        return albums
                .stream()
                .map(this::createAlbumDTO)
                .toList();
    }

    @Override
    public List<AlbumDTO> getAllInStockAlbumDTOs() {
        return createListOfAlbumDTOs(getAllAlbums())
                .stream()
                .filter(albumDTO -> albumDTO.getStock() > 0)
                .toList();
    }

    @Override
    public Album getAlbumById(Long albumId) {

        if (albumCacheService.containsKey(albumId) && albumCacheService.isValid()){
            return albumCacheService.get(albumId);
        }
        if (albumRepository.findById(albumId).isPresent()){
            Album album = albumRepository.findById(albumId).get();
            albumCacheService.put(albumId, album);
            albumCacheService.setValid(true);
            return album;
        } else{
            throw new ItemNotFoundException(String.format("Album with the id '%s' cannot be found", albumId)
            );
        }
    }

    @Override
    public AlbumDTO updateAlbumById(Long albumId, UpdateAlbumDTO updateAlbumDTO) {
        if (albumRepository.findById(albumId).isPresent()){
            albumCacheService.setValid(false);
            Album selectedAlbum = albumRepository.findById(albumId).get();

            // Updates the album title if the JSON input for "title" is not null
            if (updateAlbumDTO.getTitle() != null){
                // Will not update the album title if the input is blank or just whitespace
                if(!updateAlbumDTO.getTitle().isEmpty() && !updateAlbumDTO.getTitle().matches("(\\s+)")){
                    // Trims all the whitespace before and after the title.
                    String updatedAlbumTitle = updateAlbumDTO.getTitle().trim();
                    selectedAlbum.setTitle(updatedAlbumTitle);
                }
            }

            // Update the artist name if the JSON input for "artist" is not null
            if (updateAlbumDTO.getArtist() != null ){
                // Will not update the album title if the input is blank or just whitespace
                if (!updateAlbumDTO.getArtist().isEmpty() && !updateAlbumDTO.getArtist().matches("(\\s+)")){
                    // Trims all the whitespace before and after the artist's name.
                    String updatedArtistName = updateAlbumDTO.getArtist().trim();
                    selectedAlbum.setArtist(artistService.getOrCreateAlbumArtist(updatedArtistName));
                }
            }

            // Update the album genre if the JSON input for "genre" is not null
            if (updateAlbumDTO.getGenre() != null){
                // Will not update the album genre if the input is blank or just whitespace
                if (!updateAlbumDTO.getGenre().isEmpty() && !updateAlbumDTO.getGenre().matches("(\\s+)")){
                    String updateGenre = updateAlbumDTO.getGenre().trim();
                    selectedAlbum.setGenre(genreService.getOrCreateGenre(updateGenre));
                }
            }

            if (updateAlbumDTO.getReleaseDate() != null){
                selectedAlbum.setReleaseDate(updateAlbumDTO.getReleaseDate());
            }

            if (updateAlbumDTO.getStock() != null){
                selectedAlbum.getStock().setQuantityInStock(updateAlbumDTO.getStock());
            }

            if(updateAlbumDTO.getPrice() != null){
                selectedAlbum.setPrice(updateAlbumDTO.getPrice());
            }

            selectedAlbum.setDateModified(Instant.now());
            return createAlbumDTO(albumRepository.save(selectedAlbum));
        }else{
            throw new ItemNotFoundException(String.format("Album with the id '%s' cannot be found", albumId)
            );
        }
    }

    @Override
    public AlbumDTO returnAlbumDTOById(Long albumId) {
        return createAlbumDTO(getAlbumById(albumId));
    }

    @Override
    public AlbumDTO postNewAlbum(NewAlbumDTO newAlbumDTO) {
        return createAlbumDTO(addNewAlbum(newAlbumDTO));
    }

    @Override
    public List<AlbumDTO> getArtistAlbumsById(Long artistId) {
        if(artistService.isArtistPresent(artistId)){
            List<Album> albums = albumRepository.findByArtistId(artistId);
            return albums.stream().map(this::createAlbumDTO).toList();
        }else {
            throw  new ItemNotFoundException(String.format("Artist with the ID '%d' cannot be found", artistId));
        }
    }

    @Override
    public List<AlbumDTO> getArtistAlbumsByName(String artistName) {
        ArtistDTO artistDTO = artistService.getArtistByName(artistName);

        return albumRepository
                .findByArtistId(artistDTO.getId())
                .stream()
                .map(this::createAlbumDTO)
                .toList();
    }

    // Album to DTO mapper
    @Override
    public AlbumDTO createAlbumDTO(Album album) {

        return AlbumDTO.builder()
                .id(album.getId())
                .title(album.getTitle())
                .artist(album.getArtist().getArtistName())
                .genre(album.getGenre().getGenre())
                .releaseDate(album.getReleaseDate())
                .stock(album.getStock().getQuantityInStock())
                .price(album.getPrice())
                .dateCreated(album.getDateCreated().toString())
                .dateModified(album.getDateModified().toString())
                .build();
    }

    @Override
    public Album addNewAlbum(NewAlbumDTO NewAlbumDTO) {
        return albumRepository.save(Album.builder()
                .title(NewAlbumDTO.getTitle())
                .artist(artistService.getOrCreateAlbumArtist(NewAlbumDTO.getArtist()))
                .genre(genreService.getOrCreateGenre(NewAlbumDTO.getGenre()))
                .releaseDate(NewAlbumDTO.getReleaseDate())
                .stock(stockService.addNewStock(NewAlbumDTO.getStock()))
                .price(NewAlbumDTO.getPrice())
                .dateCreated(Instant.now())
                .dateModified((Instant.now()))
                .build());
    }

    @Override
    public Album updateAlbumStockById(Long albumId, StockDTO stockDTO) {

        Album album = getAlbumById(albumId);// If ID is not present this method should throw an error
        Stock stock = album.getStock();
        stock.setQuantityInStock(stock.getQuantityInStock() + stockDTO.getQuantityToAdd());
        album.setDateModified(Instant.now());

        album.setStock(stockService.savedUpdatedStock(stock));

        return albumRepository.save(album);
    }

    @Override
    public String deleteAlbumById(Long albumId) {
        if (albumRepository.existsById(albumId)){
            albumCacheService.remove(albumId); // remove id from the cache
            albumRepository.deleteById(albumId);
            return String.format(
                    "Album of ID '%d' has been deleted",
                    albumId
            );
        } else {
            throw new ItemNotFoundException(String.format("Album with the ID '%s' cannot be found", albumId));
        }
    }

    @Override
    public String decreaseStockByAlbumId(Long albumId) {
        if(albumRepository.findById(albumId).isPresent()){
            Album album = albumRepository.findById(albumId).get();// If ID is not present this method should throw an error
            Stock stock = album.getStock();

            if (stock.getQuantityInStock() > 0){
                stock.setQuantityInStock(stock.getQuantityInStock() - 1);
                album.setStock(stockService.savedUpdatedStock(stock));
                album.setDateModified(Instant.now());
                albumRepository.save(album);
                return String.format(
                        "Album Title: %s\nArist: %s\nQuantity in stock: %d",
                        album.getTitle(),
                        album.getArtist().getArtistName(),
                        album.getStock().getQuantityInStock()
                );
            }else {
                return String.format(
                        "Sorry, '%s' by '%s' is out of stock!",
                        album.getTitle(),
                        album.getArtist()
                );
            }

        }else {
            throw new ItemNotFoundException(String.format("Album with the ID '%s' cannot be found", albumId));
        }
    }

    @Scheduled(fixedRate = 300000)
    public void cleanUpCache(){
        System.out.println("Running Cache clean up task");
        int initialSize = albumCacheService.getCache().size(); //gets the initial size of cache HashMap
        albumCacheService.removeExpiredCacheObjects();
        int finalSize = albumCacheService.getCache().size(); //gets the final size of cache HashMap
        System.out.printf("Cache cleanup.\nRemoved %d entries%n",(initialSize - finalSize));
    }
}
