package com.northcoders.recordshopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
public class NewAlbumDTO {
    private String title;
    private String artist;
    private String genre;
    private Date releaseDate;
    private Integer stock;
    private Double price;
}
