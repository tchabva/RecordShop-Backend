package com.northcoders.recordshopbackend.repository;

import com.northcoders.recordshopbackend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtistId(long artistId);
}
