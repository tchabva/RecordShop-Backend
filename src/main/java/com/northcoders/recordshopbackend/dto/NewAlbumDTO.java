package com.northcoders.recordshopbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
public class NewAlbumDTO {

    @Valid

    @NotNull(message = "Album title is mandatory")
    @NotBlank(message = "Album title is mandatory")
    private String title;

    @NotNull(message = "An artist name is mandatory")
    @NotBlank(message = "An artist name is mandatory")
    private String artist;

    @NotNull(message = "A genre is mandatory")
    @NotBlank(message = "A genre is mandatory")
    private String genre;

    @NotNull(message = "A release date is mandatory")
    private Date releaseDate;

    @NotNull(message = "A stock quantity is mandatory")
    @PositiveOrZero(message = "A stock quantity is mandatory")
    private Integer stock;

    @NotNull(message = "A price is mandatory")
    @PositiveOrZero(message = "A stock quantity is mandatory")
    private Double price;
}
