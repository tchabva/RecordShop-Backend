package com.northcoders.recordshopbackend.service;

import com.northcoders.recordshopbackend.model.Stock;

public interface StockService {
    Stock addNewStock(int quantity);
    Stock getStockById(Long stockId);
    Stock savedUpdatedStock(Stock updatedStock);
}
