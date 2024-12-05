package com.northcoders.recordshopbackend.repository;

import com.northcoders.recordshopbackend.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
}
