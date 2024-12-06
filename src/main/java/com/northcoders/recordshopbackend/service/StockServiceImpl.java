package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.exception.ItemNotFoundException;
import com.northcoders.recordshopbackend.model.Stock;
import com.northcoders.recordshopbackend.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock addNewStock(int quantity) {
        return stockRepository.save(
                Stock.builder()
                        .quantityInStock(quantity)
                        .build()
        );
    }

    @Override
    public Stock getStockById(Long stockId) {
        if(stockRepository.findById(stockId).isPresent()){
            return stockRepository.findById(stockId).get();
        }else {
         throw new ItemNotFoundException(String.format("Stock with the id '%s' cannot be found", stockId));
        }
    }

    @Override
    public Stock savedUpdatedStock(Stock updatedStock) {
        return stockRepository.save(updatedStock);
    }
}
