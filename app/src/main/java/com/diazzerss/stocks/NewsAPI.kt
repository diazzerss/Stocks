package com.diazzerss.stocks

import com.diazzerss.stocks.model.News
import io.reactivex.Observable
import retrofit2.http.GET

/**For testing purposes only*/
private const val key = "e460b9e1d9b34ac49f7cd96e209c883b"

interface NewsAPI {

    @GET("top-headlines?category=business&country=ru&apiKey=$key")
    fun getArticlesForTicker(): Observable<News>
}