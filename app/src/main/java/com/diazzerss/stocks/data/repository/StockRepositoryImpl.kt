package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockService
import com.diazzerss.stocks.domain.model.Stock
import com.diazzerss.stocks.domain.repository.StockRepository

class StockRepositoryImpl:StockRepository {

     override suspend fun getStockActive(): ArrayList<Stock> {
        return StockService
                .instance
                .getTopActive()
    }

    override suspend fun getStockGainers(): ArrayList<Stock> {
        return StockService
                .instance
                .getTopGainers()
    }

    override suspend fun getStockLosers(): ArrayList<Stock> {
        return StockService
                .instance
                .getTopLosers()
    }
}