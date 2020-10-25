package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockService
import com.diazzerss.stocks.domain.model.Ticker
import com.diazzerss.stocks.domain.repository.TickerRepository
import io.reactivex.Single

class TickerRepositoryImpl:TickerRepository {

    override fun getTicker(query: String): Single<ArrayList<Ticker>> {
        return StockService
            .getInstance()
            .getTicker(query)
    }


}