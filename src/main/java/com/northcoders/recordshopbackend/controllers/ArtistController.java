package com.northcoders.recordshopbackend.controllers;

import com.northcoders.recordshopbackend.dto.ArtistDTO;
import com.northcoders.recordshopbackend.dto.ArtistWithAlbumsDTO;
import com.northcoders.recordshopbackend.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/all")
    public ResponseEntity<List<ArtistDTO>> getAllArtists(){
        return new ResponseEntity<>(artistService.getAllArtistsDTO(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ArtistWithAlbumsDTO> getArtistByName(@RequestParam(value = "name") String name){
        return new ResponseEntity<>(artistService.getArtistByName(name), HttpStatus.OK);
    }

    @GetMapping(path = "/{artistId}")
    public ResponseEntity<ArtistWithAlbumsDTO> getArtistByIdWithAlbums(@PathVariable("artistId") Long artistId){
        return new ResponseEntity<>(artistService.getArtistByIdWithAlbums(artistId), HttpStatus.OK);
    }
}