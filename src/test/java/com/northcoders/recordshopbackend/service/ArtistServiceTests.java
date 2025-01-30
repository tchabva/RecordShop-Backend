package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.model.Artist;
import com.northcoders.recordshopbackend.repository.ArtistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
public class ArtistServiceTests {

    @Mock
    private ArtistRepository mockArtistRepository;

    @InjectMocks
    private ArtistServiceImpl artistServiceImpl;

    @Test
    @DisplayName("Returns a list of all the artists in the DB")
    void testGetAllArtists(){
        // Arrange
        List<Artist> artists = List.of(
                Artist.builder()
                        .artistName("Davido")
                        .build(),
                Artist.builder()
                        .artistName("Marie Dahlstrom")
                        .build(),
                Artist.builder()
                        .artistName("Kendrick Lamar")
                        .build()
                );
        when(mockArtistRepository.findAll()).thenReturn(artists);

        // Act
        List<Artist> actualResult = artistServiceImpl.getAllArtists();

        // Assert
        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(artists);
    }

    @Test
    @DisplayName("Adding an Artist to the db returns a Artist object")
    void testAddArtist(){
        // Arrange
        Artist exectedArtist =Artist.builder()
                .id(1L)
                .artistName("Davido")
                .build();

        when(mockArtistRepository.save(any(Artist.class))).thenReturn(exectedArtist);

        // Act
        Artist result = artistServiceImpl.addNewArtist("Davido");

        // Assert
        assertThat(result.getArtistName()).isEqualTo(exectedArtist.getArtistName());
    }


    // TODO add more tests
}
