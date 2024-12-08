package com.northcoders.recordshopbackend.controllers;

import com.northcoders.recordshopbackend.dto.AlbumDTO;
import com.northcoders.recordshopbackend.service.AlbumService;
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
    public ResponseEntity<AlbumDTO> postAddNewAlbum(@RequestBody AlbumDTO albumDTO){
        return new ResponseEntity<>(albumService.postNewAlbum(albumDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{albumId}", produces = "application/json")
    public ResponseEntity<AlbumDTO> getByAlbumId(@PathVariable("albumId") Long albumId){
        return new ResponseEntity<>(albumService.returnAlbumDTOById(albumId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{albumId}")
    public ResponseEntity<String> deleteAlbumById(@PathVariable("albumId") Long albumId){
        return new ResponseEntity<>(albumService.deleteAlbumById(albumId), HttpStatus.OK);
    }
}
