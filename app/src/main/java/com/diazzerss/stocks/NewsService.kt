package com.diazzerss.stocks

import com.diazzerss.stocks.model.Articles
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsService {

    @GET("top-headlines?country=ru&category=business&apiKey=e460b9e1d9b34ac49f7cd96e209c883b")
    fun getArticlesForTicker(): Observable<Articles>

    companion object Factory {
        fun create(): NewsService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsapi.org/v2/")
                .build()

            return retrofit.create(NewsService::class.java)
        }
    }
}