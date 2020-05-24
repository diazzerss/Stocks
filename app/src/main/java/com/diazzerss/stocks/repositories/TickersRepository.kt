package com.diazzerss.stocks.repositories

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.models.Ticker
import io.reactivex.Single

object TickersRepository {

    fun getTicker(query: String): Single<ArrayList<Ticker>> {
        return StockService
            .create()
            .getTicker(query)
    }


}