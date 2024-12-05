package com.northcoders.recordshopbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Artist artist;

    private Genre genre;

    @Column(name = "release_date")
    private Date releaseDate;

    private Stock stock;
}
