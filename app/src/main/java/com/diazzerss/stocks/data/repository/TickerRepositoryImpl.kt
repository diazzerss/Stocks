package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockApi
import com.diazzerss.stocks.domain.model.Ticker
import com.diazzerss.stocks.domain.repository.TickerRepository
import io.reactivex.Single
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor() : TickerRepository {


    @Inject
    lateinit var stockApi: StockApi

    override fun getTicker(query: String): Single<ArrayList<Ticker>> {
        return stockApi.getTicker(query)
    }


}