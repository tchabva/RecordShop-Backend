package com.northcoders.recordshopbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "artist_name")
    private String artistName;

    @OneToMany(mappedBy = "artist")
    private Set<Album> albums;
}
