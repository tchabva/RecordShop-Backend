package com.northcoders.recordshopbackend.repository;

import com.northcoders.recordshopbackend.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByArtistName(String artistName);

    @Query("SELECT a FROM Artist a LEFT JOIN FETCH a.albums WHERE a.id = :artistId")
    Optional<Artist> findByIdWithAlbums(@Param("artistId") Long artistId);
}
