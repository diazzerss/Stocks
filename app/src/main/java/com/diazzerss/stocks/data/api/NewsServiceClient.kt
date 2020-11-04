package com.diazzerss.stocks.data.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NewsServiceClient {

        val instance: NewsApiInterface = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsapi.org/v2/")
                .build()
                .create(NewsApiInterface::class.java)

}