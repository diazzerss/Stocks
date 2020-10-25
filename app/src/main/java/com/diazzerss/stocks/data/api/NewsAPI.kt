package com.diazzerss.stocks.data.api

import com.diazzerss.stocks.domain.model.News
import retrofit2.http.GET

interface NewsAPI {

    @GET("top-headlines?category=business&country=ru&apiKey=e460b9e1d9b34ac49f7cd96e209c883b")
    suspend fun getArticlesForTicker(): News

}
