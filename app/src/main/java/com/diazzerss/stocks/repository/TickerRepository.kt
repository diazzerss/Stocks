package com.diazzerss.stocks.repository

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.model.Ticker
import io.reactivex.Single

object TickerRepository {

    fun getTicker(query: String): Single<ArrayList<Ticker>> {
        return StockService
            .getInstance()
            .getTicker(query)
    }


}