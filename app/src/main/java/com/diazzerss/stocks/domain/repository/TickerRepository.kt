package com.diazzerss.stocks.domain.repository

import com.diazzerss.stocks.domain.model.Ticker
import io.reactivex.Single

interface TickerRepository {
    fun getTicker(query: String): Single<ArrayList<Ticker>>
}