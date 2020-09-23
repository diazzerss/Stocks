package com.diazzerss.stocks.repository

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.model.Stock
import io.reactivex.Single

object StockRepository {

    fun getStockActive(): Single<ArrayList<Stock>> {
        return StockService
            .getInstance()
            .getTopActive()
    }

    fun getStockGainers(): Single<ArrayList<Stock>> {
        return StockService
            .getInstance()
            .getTopGainers()
    }

    fun getStockLosers(): Single<ArrayList<Stock>> {
        return StockService
            .getInstance()
            .getTopLosers()
    }
}