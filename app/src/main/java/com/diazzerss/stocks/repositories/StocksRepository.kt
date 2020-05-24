package com.diazzerss.stocks.repositories

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.models.Stock
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Diaz on 06.05.2020.
 */
object StocksRepository {

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