package com.northcoders.recordshopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ArtistDTO {
    private Long id;
    private String artistName;
}
