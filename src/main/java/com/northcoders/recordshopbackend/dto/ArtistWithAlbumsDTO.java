package com.northcoders.recordshopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ArtistWithAlbumsDTO {
    private Long id;
    private String artistName;
    private List<AlbumDTO> albums;
}
