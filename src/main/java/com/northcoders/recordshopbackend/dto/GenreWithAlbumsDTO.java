package com.northcoders.recordshopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GenreWithAlbumsDTO {
    private Long id;
    private String genre;
    private List<AlbumDTO> albumDTOS;
}
