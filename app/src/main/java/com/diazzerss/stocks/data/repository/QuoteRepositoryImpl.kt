package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockService
import com.diazzerss.stocks.domain.model.Quote
import com.diazzerss.stocks.domain.repository.QuoteRepository

class QuoteRepositoryImpl : QuoteRepository {

    override suspend fun getQuote(ticker: String): ArrayList<Quote> {
        return StockService
                .getInstance()
                .getQuote(ticker)
    }

}
