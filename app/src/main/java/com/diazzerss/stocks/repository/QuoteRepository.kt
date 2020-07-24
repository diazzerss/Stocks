package com.diazzerss.stocks.repository

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.model.Quote
import io.reactivex.Observable

object QuoteRepository {

    fun getQuote(ticker: String): Observable<ArrayList<Quote>> {
        return StockService
            .getInstance()
            .getQuote(ticker)
    }

}
