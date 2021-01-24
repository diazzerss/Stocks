package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockApi
import com.diazzerss.stocks.domain.model.Stock
import com.diazzerss.stocks.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor() : StockRepository{


    @Inject
    lateinit var stockApi: StockApi

    override suspend fun getStockActive(): ArrayList<Stock> {
        return stockApi.getTopActive()
    }

    override suspend fun getStockGainers(): ArrayList<Stock> {
        return stockApi.getTopGainers()
    }

    override suspend fun getStockLosers(): ArrayList<Stock> {
        return stockApi.getTopLosers()
    }
}