package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockApi
import com.diazzerss.stocks.domain.model.Quote
import com.diazzerss.stocks.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor() : QuoteRepository {

    @Inject
    lateinit var stockApi: StockApi
    override suspend fun getQuote(ticker: String): ArrayList<Quote> {
        return stockApi.getQuote(ticker)
    }

}
