package com.diazzerss.stocks.repository

import com.diazzerss.stocks.NewsService
import com.diazzerss.stocks.model.Articles
import io.reactivex.Observable

object ArticleRepository {
    fun getArticles(): Observable<Articles> {
        return NewsService
            .create()
            .getArticlesForTicker()
    }
}