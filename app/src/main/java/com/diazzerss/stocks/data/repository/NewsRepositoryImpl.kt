package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.NewsApi
import com.diazzerss.stocks.domain.model.News
import com.diazzerss.stocks.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor()  : NewsRepository {

    @Inject lateinit var newsApi: NewsApi

    override suspend fun getNews(): News {
        return newsApi.getNewsForTicker()
    }

}