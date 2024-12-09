package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.model.Album;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Data
public class AlbumCache {
    private final HashMap<Long, Album> albumDTOCache = new HashMap<>();
    private boolean isValid;

}
