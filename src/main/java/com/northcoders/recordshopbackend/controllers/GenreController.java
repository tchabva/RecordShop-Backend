package com.northcoders.recordshopbackend.controllers;

import com.northcoders.recordshopbackend.dto.GenreDTO;
import com.northcoders.recordshopbackend.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres(){
        return new ResponseEntity<>(genreService.getAllGenres(), HttpStatus.OK);
    }


}
