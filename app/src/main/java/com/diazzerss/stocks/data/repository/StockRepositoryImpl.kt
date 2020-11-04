package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockApiInterface
import com.diazzerss.stocks.domain.model.Stock
import com.diazzerss.stocks.domain.repository.StockRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class StockRepositoryImpl : StockRepository, KoinComponent {

    private val stockServiceClient: StockApiInterface by inject()

    override suspend fun getStockActive(): ArrayList<Stock> {
        return stockServiceClient.getTopActive()
    }

    override suspend fun getStockGainers(): ArrayList<Stock> {
        return stockServiceClient.getTopGainers()
    }

    override suspend fun getStockLosers(): ArrayList<Stock> {
        return stockServiceClient.getTopLosers()
    }
}