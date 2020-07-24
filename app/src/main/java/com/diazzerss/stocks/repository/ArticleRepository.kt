package com.diazzerss.stocks.repository

import com.diazzerss.stocks.NewsService
import com.diazzerss.stocks.model.News
import io.reactivex.Observable

object ArticleRepository {
    fun getArticles(): Observable<News> {
        return NewsService
            .getInstance()
            .getArticlesForTicker()
    }
}