package com.northcoders.recordshopbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "artist_name")
    private String artistName;

    @OneToMany(mappedBy = "artist")
    private Set<Album> albums;
}
