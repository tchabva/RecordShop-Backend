package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.dto.AlbumDTO;
import com.northcoders.recordshopbackend.dto.StockDTO;
import com.northcoders.recordshopbackend.model.Album;
import com.northcoders.recordshopbackend.model.Artist;
import com.northcoders.recordshopbackend.model.Stock;
import com.northcoders.recordshopbackend.model.Genre;
import com.northcoders.recordshopbackend.repository.AlbumRepository;
import com.northcoders.recordshopbackend.exception.ItemNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
public class AlbumServiceTests {

    @Mock
    private AlbumRepository mockAlbumRepository;
    @Mock
    private ArtistService artistService;
    @Mock
    private StockService stockService;
    @Mock
    private CacheService<Album> albumCacheService;
    @Mock
    private GenreService genreService;
    @Mock
    private Instant instant;

    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;

    @Test
    @DisplayName("Returns a list of all the Albums in stock")
    void testGetAllAlbums(){
        // Arrange
        List<Album> albums = List.of(
                Album.builder()
                        .title("Timeless")
                        .artist(Artist.builder().artistName("Davido").build())
                        .genre(Genre.builder().genre( "Afrobeats").build())
                        .releaseDate(Date.valueOf("2023-01-12"))
                        .stock(Stock.builder().quantityInStock(4).build())
                        .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .price(9.99)
                        .build(),
                Album.builder()
                        .title("A Good Time")
                        .artist(Artist.builder().artistName("Marie Dahlstrom").build())
                        .genre(Genre.builder().genre("R&B").build())
                        .releaseDate(Date.valueOf("2023-06-07"))
                        .stock(Stock.builder().quantityInStock(4).build())
                        .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .price(9.99)
                        .build(),
                Album.builder()
                        .title("GNX")
                        .artist(Artist.builder().artistName("Kendrick Lamar").build())
                        .genre(Genre.builder().genre("Rap").build())
                        .releaseDate(Date.valueOf("2024-11-22"))
                        .stock(Stock.builder().quantityInStock(4).build())
                        .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .price(9.99)
                        .build()
        );

        when(mockAlbumRepository.findAll()).thenReturn(albums);

        // Act
        List<Album> actualResult = albumServiceImpl.getAllAlbums();

        // Assert
        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(albums);
    }

    @Test
    @DisplayName("Returns album when a valid Id is supplied")
    void testGetAlbumById(){
        // Arrange
        Album timeless = Album.builder()
                .title("Timeless")
                .artist(Artist.builder().artistName("Davido").build())
                .genre(Genre.builder().genre("Afrobeats").build())
                .releaseDate(Date.valueOf("2023-01-12"))
                .price(9.99)
                .stock(Stock.builder().quantityInStock(4).build())
                .build();

        when(mockAlbumRepository.findById(2L)).thenReturn(Optional.of(timeless));

        // Act
        Album actualResult = albumServiceImpl.getAlbumById(2L);

        // Assert
        assertThat(actualResult.getTitle()).isEqualTo(timeless.getTitle());
        assertThat(actualResult.getArtist()).isEqualTo(timeless.getArtist());
        assertThat(actualResult.getReleaseDate()).isEqualTo(timeless.getReleaseDate());
    }

    @Test
    @DisplayName("Throws an ItemNotFoundException for an invalid ID")
    void testGetAlbumByIdForInvalidId(){
        // Arrange
        Long invalidID = 1L;

        when(mockAlbumRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatExceptionOfType(ItemNotFoundException.class)
                .isThrownBy(() ->
                        albumServiceImpl.getAlbumById(invalidID))
                .withMessageMatching("Album with the id '\\d+' cannot be found"
                );
    }

    @Test
    @DisplayName("Returns album when a valid AlbumDTO is supplied")
    void testCreatesAnAlbumFromAnAlbumDTO(){

        // Arrange
        AlbumDTO goodTimeDTO = AlbumDTO.builder()
                .title("A Good Time")
                .artist("Marie Dahlstrom")
                .genre("R&B")
                .releaseDate(Date.valueOf("2023-06-07"))
                .price(9.99)
                .stock(4)
                .build();

        Album aGoodTime = Album.builder()
                .title("A Good Time")
                .artist(Artist.builder().artistName("Marie Dahlstrom").build())
                .genre(Genre.builder().genre("R&B").build())
                .releaseDate(Date.valueOf("2023-06-07"))
                .stock(Stock.builder().quantityInStock(4).build())
                .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                .price(9.99)
                .build();

        // Act
        AlbumDTO actualResult = albumServiceImpl.createAlbumDTO(aGoodTime);

        // Assert
        assertThat(actualResult).isInstanceOf(AlbumDTO.class);
        assertThat(actualResult.getArtist()).isEqualTo(goodTimeDTO.getArtist());
        assertThat(actualResult.getReleaseDate()).isEqualTo(goodTimeDTO.getReleaseDate());
        assertThat(actualResult.getDateCreated()).isEqualTo("2024-12-13T12:00:00Z");
    }

    @Test
    @DisplayName("Returns a list of AlbumDTO from a list of Albums")
    void testGetListOfAlbumDTOs(){

        // Arrange
        List<Album> albums = List.of(
                Album.builder()
                        .title("Timeless")
                        .artist(Artist.builder().artistName("Davido").build())
                        .genre(Genre.builder().genre( "Afrobeats").build())
                        .releaseDate(Date.valueOf("2023-01-12"))
                        .stock(Stock.builder().quantityInStock(4).build())
                        .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .price(9.99)
                        .build(),
                Album.builder()
                        .title("A Good Time")
                        .artist(Artist.builder().artistName("Marie Dahlstrom").build())
                        .genre(Genre.builder().genre("R&B").build())
                        .releaseDate(Date.valueOf("2023-06-07"))
                        .stock(Stock.builder().quantityInStock(4).build())
                        .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .price(9.99)
                        .build(),
                Album.builder()
                        .title("GNX")
                        .artist(Artist.builder().artistName("Kendrick Lamar").build())
                        .genre(Genre.builder().genre("Rap").build())
                        .releaseDate(Date.valueOf("2024-11-22"))
                        .stock(Stock.builder().quantityInStock(4).build())
                        .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .price(9.99)
                        .build()
        );

        // Act
        List<AlbumDTO> actualResult = albumServiceImpl.createListOfAlbumDTOs(albums);

        // Assert
        assertThat(actualResult).hasSize(3);
        assertThat(actualResult.getFirst()).isInstanceOf(AlbumDTO.class);
        assertThat(actualResult.get(1)).isInstanceOf(AlbumDTO.class);
        assertThat(actualResult.getLast()).isInstanceOf(AlbumDTO.class);
    }

    @Test
    @DisplayName("Returns a list of AlbumDTOs that have a stock quantity more than 0")
    void testGetListOfInStockAlbumDTOs(){

        // Arrange
        List<Album> albums = List.of(
                Album.builder()
                        .title("Timeless")
                        .artist(Artist.builder().artistName("Davido").build())
                        .genre(Genre.builder().genre( "Afrobeats").build())
                        .releaseDate(Date.valueOf("2023-01-12"))
                        .stock(Stock.builder().quantityInStock(4).build())
                        .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .price(9.99)
                        .build(),
                Album.builder()
                        .title("A Good Time")
                        .artist(Artist.builder().artistName("Marie Dahlstrom").build())
                        .genre(Genre.builder().genre("R&B").build())
                        .releaseDate(Date.valueOf("2023-06-07"))
                        .stock(Stock.builder().quantityInStock(0).build())
                        .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .price(9.99)
                        .build(),
                Album.builder()
                        .title("GNX")
                        .artist(Artist.builder().artistName("Kendrick Lamar").build())
                        .genre(Genre.builder().genre("Rap").build())
                        .releaseDate(Date.valueOf("2024-11-22"))
                        .stock(Stock.builder().quantityInStock(0).build())
                        .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                        .price(9.99)
                        .build()
        );

        when(mockAlbumRepository.findAll()).thenReturn(albums);

        // Act
        List<AlbumDTO> inStockAlbumDTOs = albumServiceImpl.getAllInStockAlbumDTOs();

        // Assert
        assertThat(inStockAlbumDTOs).hasSize(1);
        assertThat(inStockAlbumDTOs.getFirst().getStock()).isEqualTo(4);
        assertThat(inStockAlbumDTOs.getFirst().getTitle()).isEqualTo("Timeless");
    }

    @Test
    @DisplayName("Returns album when an AlbumDTO is supplied and saved to the DB")
    void testAddAlbum(){
        // Arrange
        AlbumDTO timelessDTO = AlbumDTO.builder()
                .title("Timeless")
                .artist("Davido")
                .genre("Afrobeats")
                .releaseDate(Date.valueOf("2023-01-12"))
                .price(9.99)
                .stock(4)
                .build();

        Album timelessAlbum = Album.builder()
                .title("Timeless")
                .artist(Artist.builder().artistName("Davido").build())
                .genre(Genre.builder().genre( "Afrobeats").build())
                .releaseDate(Date.valueOf("2023-01-12"))
                .stock(Stock.builder().quantityInStock(4).build())
                .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                .price(9.99)
                .build();

        when(mockAlbumRepository.save(any(Album.class))).thenReturn(timelessAlbum);

        when(artistService.getOrCreateAlbumArtist(timelessDTO.getArtist()))
                .thenReturn(timelessAlbum.getArtist());

        when(genreService.getOrCreateGenre(timelessDTO.getGenre()))
                .thenReturn(timelessAlbum.getGenre());

        when(stockService.addNewStock(timelessDTO.getStock()))
                .thenReturn(timelessAlbum.getStock());

        // Act
        Album actualResult = albumServiceImpl.addNewAlbum(timelessDTO);

        // Assert
        assertThat(actualResult.getTitle()).isEqualTo(timelessAlbum.getTitle());
        assertThat(actualResult.getArtist()).isEqualTo(timelessAlbum.getArtist());
        assertThat(actualResult.getReleaseDate()).isEqualTo(timelessAlbum.getReleaseDate());
        assertThat(actualResult.getStock()).isEqualTo(timelessAlbum.getStock());
        assertThat(actualResult.getPrice()).isEqualTo(timelessAlbum.getPrice());
        assertThat(actualResult.getGenre()).isEqualTo(timelessAlbum.getGenre());

        verify(mockAlbumRepository).save(any(Album.class));
    }

    @Test
    @DisplayName("Returns can edit album ")
    void testEditAlbumStockById(){
        // Arrange
        Long inputId = 3L;

        StockDTO stockDTO = new StockDTO(2);

        Album timelessAlbum = Album.builder()
                .id(3L)
                .title("Timeless")
                .artist(Artist.builder().artistName("Davido").build())
                .genre(Genre.builder().genre( "Afrobeats").build())
                .releaseDate(Date.valueOf("2023-01-12"))
                .stock(Stock.builder().id(3L).quantityInStock(4).build())
                .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                .price(9.99)
                .build();

        Stock updatedStock = Stock.builder()
                .id(3L)
                .quantityInStock(6)
                .build();

        when(mockAlbumRepository.findById(inputId)).thenReturn(Optional.of(timelessAlbum));

        when(stockService.savedUpdatedStock(updatedStock)).thenReturn(updatedStock);

        when(mockAlbumRepository.save(timelessAlbum)).thenReturn(timelessAlbum);

        // Act
        Album actualResult = albumServiceImpl.updateAlbumStockById(inputId, stockDTO);

        // Assert
        assertThat(actualResult.getStock().getId()).isEqualTo(3L);
        assertThat(actualResult.getStock().getQuantityInStock()).isEqualTo(6);
    }

    @Test
    @DisplayName("Returns Decreases the album stock by 1 when a valid Id is supplied")
    void testDeleteAlbumStockById(){
        // Arrange
        Long id = 2L;

        String expectedOutput = "Album Title: Timeless\nArist: Davido\nQuantity in stock: 3";

        Album timeless = Album.builder()
                .title("Timeless")
                .artist(Artist.builder().artistName("Davido").build())
                .genre(Genre.builder().genre( "Afrobeats").build())
                .releaseDate(Date.valueOf("2023-01-12"))
                .stock(Stock.builder().quantityInStock(4).build())
                .dateCreated(Instant.parse("2024-12-13T12:00:00.00Z"))
                .dateModified(Instant.parse("2024-12-13T12:00:00.00Z"))
                .price(9.99)
                .build();

        Stock updatedStock = timeless.getStock();

        when(mockAlbumRepository.findById(id)).thenReturn(Optional.of(timeless));

        when(stockService.savedUpdatedStock(updatedStock)).thenReturn(updatedStock);

        when(mockAlbumRepository.save(timeless)).thenReturn(timeless);

        // Act
        String actualResult = albumServiceImpl.decreaseStockByAlbumId(id);

        // Assert
        assertThat(actualResult).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("deleteAlbumById returns String confirmation of the deleted album")
    void testDeleteAlbumById(){
        // Arrange
        Long id = 1L;
        String expectedString = "Album of ID '\\d+' has been deleted";
        when(mockAlbumRepository.existsById(id)).thenReturn(true);

        // Act
        String actualString = albumServiceImpl.deleteAlbumById(id);

        // Assert
        assertThat(actualString).matches(expectedString);
    }

    @Test
    @DisplayName("Throws an ItemNotFoundException for an invalid ID for DeleteMapping")
    void testDeleteAlbumByIdForInvalidId(){
        // Arrange
        Long invalidID = 1L;

        when(mockAlbumRepository.existsById(invalidID)).thenReturn(false);

        // Act & Assert
        assertThatExceptionOfType(ItemNotFoundException.class)
                .isThrownBy(() ->
                        albumServiceImpl.deleteAlbumById(invalidID))
                .withMessageMatching("Album with the ID '\\d+' cannot be found"
                );
    }
}