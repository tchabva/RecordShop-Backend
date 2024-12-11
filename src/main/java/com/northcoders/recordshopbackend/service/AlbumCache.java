package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.model.Album;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Data
public class AlbumCache {
    private final HashMap<Long, Album> albumCache = new HashMap<>();
    private boolean isValid;

    public void put(Long albumId, Album album){
        this.albumCache.put(albumId, album);
    }

    public boolean containsKey(Long albumId){
        return this.albumCache.containsKey(albumId);
    }

    public Album get(Long albumId){
        return this.albumCache.get(albumId);
    }

    public void remove(Long albumId){
        this.albumCache.remove(albumId);
    }

}
