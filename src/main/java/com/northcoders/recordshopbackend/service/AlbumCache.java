package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.model.Album;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Data
public class AlbumCache {
    private final HashMap<Long, AlbumCacheObject> albumCache = new HashMap<>();
    private final long TIME_TO_LIVE = 20000;
    private boolean isValid;

    @Data
    protected static class AlbumCacheObject{
        private long lastAccessed = System.currentTimeMillis();
        private Album cachedAlbum;

        public AlbumCacheObject(Album cachedAlbum) {
            this.cachedAlbum = cachedAlbum;
        }
    }

    // Creates an AlbumCacheObject and then puts this into the HashMap with it's associated albumId
    public void put(Long albumId, Album album){
        AlbumCacheObject albumCacheObject = new AlbumCacheObject(album);
        this.albumCache.put(albumId, albumCacheObject);
    }

    public boolean containsKey(Long albumId){
        return this.albumCache.containsKey(albumId);
    }

    public Album get(Long albumId){
        System.out.printf("Retrieved ID: %d from cache", albumId);
        albumCache.get(albumId).setLastAccessed(System.currentTimeMillis());
        return this.albumCache.get(albumId).getCachedAlbum();
    }

    public void remove(Long albumId){
        this.albumCache.remove(albumId);
    }

    public void removeExpiredCacheObjects(){
        long currentTime = System.currentTimeMillis();
        System.out.println("Purging expired cache data... ");
        albumCache.values().removeIf(cacheObject ->
                currentTime - cacheObject.getLastAccessed() > getTIME_TO_LIVE()
        );
        System.out.println("Purge executed");
    }
}
