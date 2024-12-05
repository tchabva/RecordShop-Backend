package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.model.Album;
import com.northcoders.recordshopbackend.model.Artist;
import com.northcoders.recordshopbackend.model.Stock;
import com.northcoders.recordshopbackend.model.enums.Genre;
import com.northcoders.recordshopbackend.repository.AlbumRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class AlbumServiceTests {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;

    @Test
    @DisplayName("Returns a list of all the Albums in stock")
    void testGetAllAlbums(){
        // Arrange
        List<Album> albums = List.of(
                Album.builder()
                        .title("Timeless")
                        .artist(Artist.builder()
                                .artistName("Davido")
                                .build())
                        .genre(Genre.AFROBEATS)
                        .releaseDate(Date.valueOf("2023-01-12"))
                        .stock(Stock.builder()
                                .quantityInStock(4)
                                .build())
                        .build(),
                Album.builder()
                        .title("A Good Time")
                        .artist(Artist.builder()
                                .artistName("Marie Dahlstrom")
                                .build())
                        .genre(Genre.RNB)
                        .releaseDate(Date.valueOf("2023-06-07"))
                        .stock(Stock.builder()
                                .quantityInStock(4)
                                .build())
                        .build(),
                Album.builder()
                        .title("GNX")
                        .artist(Artist.builder()
                                .artistName("Kendrick Lamar")
                                .build())
                        .genre(Genre.RNB)
                        .releaseDate(Date.valueOf("2024-11-22"))
                        .stock(Stock.builder()
                                .quantityInStock(4)
                                .build())
                        .build()
        );

        when(albumRepository.findAll()).thenReturn(albums);

        // Act
        List<Album> actualResult = albumServiceImpl.getAllAlbums();

        // Assert
        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(albums);
    }

}
