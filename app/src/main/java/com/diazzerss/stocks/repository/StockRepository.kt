package com.diazzerss.stocks.repository

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.model.Stock
import io.reactivex.Observable

object StockRepository {

    fun getStockActive(): Observable<ArrayList<Stock>> {
        return StockService
            .create()
            .getTopActive()
    }

    fun getStockGainers(): Observable<ArrayList<Stock>> {
        return StockService
            .create()
            .getTopGainers()
    }

    fun getStockLosers(): Observable<ArrayList<Stock>> {
        return StockService
            .create()
            .getTopLosers()
    }
}