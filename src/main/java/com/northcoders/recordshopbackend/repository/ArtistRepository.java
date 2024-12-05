package com.northcoders.recordshopbackend.repository;

import com.northcoders.recordshopbackend.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
