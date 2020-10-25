package com.diazzerss.stocks.domain.repository

import com.diazzerss.stocks.domain.model.News

interface ArticleRepository {
    suspend fun getArticles(): News
}