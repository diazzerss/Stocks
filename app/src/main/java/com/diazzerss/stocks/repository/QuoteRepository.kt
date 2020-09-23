package com.diazzerss.stocks.repository

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.model.Quote
import io.reactivex.Single

object QuoteRepository {

    fun getQuote(ticker: String): Single<ArrayList<Quote>> {
        return StockService
            .getInstance()
            .getQuote(ticker)
    }

}
