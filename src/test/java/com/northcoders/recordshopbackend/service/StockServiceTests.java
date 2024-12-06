package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.exception.ItemNotFoundException;
import com.northcoders.recordshopbackend.model.Stock;
import com.northcoders.recordshopbackend.repository.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@DataJpaTest
public class StockServiceTests {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockServiceImpl;

    @Test
    @DisplayName("Adding a Stock item to the db returns a Stock object")
    void testAddStock(){
        // Arrange
        Stock exectedStock = Stock.builder()
                .quantityInStock(4)
                .build();

        when(stockRepository.save(exectedStock)).thenReturn(exectedStock);

        // Act
        Stock result = stockServiceImpl.addNewStock(exectedStock.getQuantityInStock());

        // Assert
        assertThat(result.getQuantityInStock()).isEqualTo(exectedStock.getQuantityInStock());
    }

    @Test
    @DisplayName("Returns a Stock item when provided a valid stock Id")
    void testGetStockById(){
        // Arrange
        Stock exectedStock = Stock.builder()
                .quantityInStock(1)
                .build();

        when(stockRepository.findById(2L)).thenReturn(Optional.of(exectedStock));

        // Act
        Stock result = stockServiceImpl.getStockById(2L);

        // Assert
        assertThat(result.getQuantityInStock()).isEqualTo(exectedStock.getQuantityInStock());
    }

    @Test
    @DisplayName("Throws a ItemNotFoundException when provided a invalid stock Id")
    void testGetStockByIdForInvalidID(){
        // Arrange
        Long invalidID = 4L;


        when(stockRepository.findById(invalidID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatExceptionOfType(ItemNotFoundException.class)
                .isThrownBy( () -> {
                    Stock result = stockServiceImpl.getStockById(2L);
                }).withMessageMatching("Stock with the id '\\d+' cannot be found");
    }
}
