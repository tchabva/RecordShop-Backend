package com.northcoders.recordshopbackend.dto;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;


import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
public class UpdateAlbumDTO {

    @Valid

    private String title;

    private String artist;

    private String genre;

    private Date releaseDate;

    @PositiveOrZero(message = "A stock quantity cannot be negative")
    private Integer stock;

    @PositiveOrZero(message = "A price cannot be negative")
    private Double price;

    private String artworkUrl;
}

