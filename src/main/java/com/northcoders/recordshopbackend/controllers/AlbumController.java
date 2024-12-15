package com.northcoders.recordshopbackend.controllers;

import com.northcoders.recordshopbackend.dto.AlbumDTO;
import com.northcoders.recordshopbackend.dto.NewAlbumDTO;
import com.northcoders.recordshopbackend.dto.UpdateAlbumDTO;
import com.northcoders.recordshopbackend.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/v1/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllInStockAlbums(){
        return new ResponseEntity<>(albumService.getAllInStockAlbumDTOs(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AlbumDTO> postAddNewAlbum(@Valid @RequestBody NewAlbumDTO newAlbumDTO){
        return new ResponseEntity<>(albumService.postNewAlbum(newAlbumDTO), HttpStatus.CREATED);
    }

    @PostMapping("/add-albums")
    private ResponseEntity<List<AlbumDTO>> addAlbums(@Valid @RequestBody List<NewAlbumDTO> newAlbumDTOS){
        newAlbumDTOS.forEach(newAlbumDTO -> albumService.postNewAlbum(newAlbumDTO));
        return new ResponseEntity<>(albumService.getAllInStockAlbumDTOs(), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{albumId}", produces = "application/json")
    public ResponseEntity<AlbumDTO> getByAlbumId(@PathVariable("albumId") Long albumId){
        return new ResponseEntity<>(albumService.returnAlbumDTOById(albumId), HttpStatus.OK);
    }

    @GetMapping(path = "/artist/{artistId}")
    public ResponseEntity<List<AlbumDTO>> getAllAlbumsByArtistID(@PathVariable("artistId") Long artistId){
        return new ResponseEntity<>(albumService.getArtistAlbumsById(artistId), HttpStatus.OK);
    }

    @GetMapping("/artist")
    public ResponseEntity<List<AlbumDTO>> getAllAlbumsByArtistName(@RequestParam("name") String artistName){
        return new ResponseEntity<>(albumService.getArtistAlbumsByName(artistName), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{albumId}")
    public ResponseEntity<String> deleteAlbumById(@PathVariable("albumId") Long albumId){
        return new ResponseEntity<>(albumService.deleteAlbumById(albumId), HttpStatus.OK);
    }

    @PutMapping(path = "/{albumId}")
    public ResponseEntity<AlbumDTO> updateAlbumById(
            @PathVariable("albumId") Long albumId,
            @Valid @RequestBody UpdateAlbumDTO updateAlbumDTO
    ){
        return new ResponseEntity<>(albumService.updateAlbumById(albumId, updateAlbumDTO), HttpStatus.CREATED);
    }
}
