package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.NewsApiInterface
import com.diazzerss.stocks.data.api.NewsServiceClient
import com.diazzerss.stocks.domain.model.News
import com.diazzerss.stocks.domain.repository.ArticleRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class ArticleRepositoryImpl : ArticleRepository, KoinComponent {

    private val newsServiceClient: NewsApiInterface by inject()

    override suspend fun getArticles(): News {
        return newsServiceClient.getArticlesForTicker()
    }

}