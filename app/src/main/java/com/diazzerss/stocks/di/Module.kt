package com.diazzerss.stocks.di

import com.diazzerss.stocks.data.api.NewsApiInterface
import com.diazzerss.stocks.data.api.NewsServiceClient
import com.diazzerss.stocks.data.api.StockApiInterface
import com.diazzerss.stocks.data.api.StockServiceClient
import org.koin.dsl.module

val dataModule = module {
    single<StockApiInterface> { StockServiceClient.instance }
    single<NewsApiInterface> { NewsServiceClient.instance }
}

val domainModule = module {
}

val presentationModule = module {
}