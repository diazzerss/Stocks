package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockApiInterface
import com.diazzerss.stocks.domain.model.Quote
import com.diazzerss.stocks.domain.repository.QuoteRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class QuoteRepositoryImpl : QuoteRepository, KoinComponent {

    private val stockServiceClient: StockApiInterface by inject()

    override suspend fun getQuote(ticker: String): ArrayList<Quote> {
        return stockServiceClient.getQuote(ticker)
    }

}
