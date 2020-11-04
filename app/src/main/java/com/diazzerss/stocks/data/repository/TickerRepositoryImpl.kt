package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockApiInterface
import com.diazzerss.stocks.domain.model.Ticker
import com.diazzerss.stocks.domain.repository.TickerRepository
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class TickerRepositoryImpl : TickerRepository, KoinComponent {

    private val stockServiceClient: StockApiInterface by inject()

    override fun getTicker(query: String): Single<ArrayList<Ticker>> {
        return stockServiceClient.getTicker(query)
    }


}