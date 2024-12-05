package com.northcoders.recordshopbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "artist_name")
    private String artistName;

    private List<Album> albums;
}
