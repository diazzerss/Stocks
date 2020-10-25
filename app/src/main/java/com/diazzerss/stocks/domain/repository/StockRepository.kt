package com.diazzerss.stocks.domain.repository

import com.diazzerss.stocks.domain.model.Stock

interface StockRepository {
    suspend fun getStockActive(): ArrayList<Stock>

    suspend fun getStockGainers(): ArrayList<Stock>

    suspend fun getStockLosers(): ArrayList<Stock>
}