package com.diazzerss.stocks.domain.repository

import com.diazzerss.stocks.domain.model.News

interface NewsRepository {
    suspend fun getNews(): News
}