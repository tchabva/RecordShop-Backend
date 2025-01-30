package com.northcoders.recordshopbackend.repository;

import com.northcoders.recordshopbackend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {}
