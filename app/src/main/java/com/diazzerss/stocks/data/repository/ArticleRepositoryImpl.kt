package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.NewsService
import com.diazzerss.stocks.domain.model.News
import com.diazzerss.stocks.domain.repository.ArticleRepository

class ArticleRepositoryImpl : ArticleRepository {
    override suspend fun getArticles(): News {
        return NewsService.getInstance().getArticlesForTicker()
    }

}