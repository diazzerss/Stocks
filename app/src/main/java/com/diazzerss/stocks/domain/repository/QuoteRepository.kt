package com.diazzerss.stocks.domain.repository

import com.diazzerss.stocks.domain.model.Quote

interface QuoteRepository {
    suspend fun getQuote(ticker: String): ArrayList<Quote>
}