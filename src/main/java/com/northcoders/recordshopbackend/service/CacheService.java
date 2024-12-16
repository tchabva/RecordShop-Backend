package com.northcoders.recordshopbackend.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Data
public class CacheService<T> {
    private final HashMap<Long, CacheObject<T>> cache = new HashMap<>();
    private final long TIME_TO_LIVE = 300000;
    private boolean isValid;

    @Data
    protected static class CacheObject<T> {
        private long lastAccessed = System.currentTimeMillis();
        private T cachedObject;

        public CacheObject(T cachedObject) {
            this.cachedObject = cachedObject;
        }
    }

    // Creates an CacheObject and then puts this into the HashMap with it's associated object id
    public void put(Long id, T object){
        CacheObject<T> cacheObject = new CacheObject<T>(object);
        this.cache.put(id, cacheObject);
    }

    public boolean containsKey(Long id){
        return this.cache.containsKey(id);
    }

    public T get(Long id){
        System.out.printf("Retrieved ID: %d from cache", id);
        cache.get(id).setLastAccessed(System.currentTimeMillis());
        return this.cache.get(id).getCachedObject();
    }

    public void remove(Long id){
        this.cache.remove(id);
    }

    public void removeExpiredCacheObjects(){
        long currentTime = System.currentTimeMillis();
        System.out.println("Purging expired cache data... ");
        cache.values().removeIf(cacheObject ->
                currentTime - cacheObject.getLastAccessed() > getTIME_TO_LIVE()
        );
        System.out.println("Purge executed");
    }
}
