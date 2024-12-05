package com.northcoders.recordshopbackend.model;

import com.northcoders.recordshopbackend.model.enums.Genre;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column
    private Genre genre;

    @Column(name = "release_date")
    private Date releaseDate;

    @OneToOne
    private Stock stock;
}
