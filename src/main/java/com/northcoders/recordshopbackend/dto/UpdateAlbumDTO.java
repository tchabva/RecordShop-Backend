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

    @NotBlank(message = "Album title is cannot be blank or empty")
    private String title;

    @NotBlank(message = "An artist name cannot be blank or empty")
    private String artist;

    @NotBlank(message = "A genre is cannot be blank or empty")
    private String genre;

    @NotBlank(message = "A genre is cannot be blank or empty")
    private Date releaseDate;

    @PositiveOrZero(message = "A stock quantity cannot be negative")
    private Integer stock;

    @PositiveOrZero(message = "A price cannot be negative")
    private Double price;
}

