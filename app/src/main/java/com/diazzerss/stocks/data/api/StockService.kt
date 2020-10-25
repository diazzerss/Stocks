package com.diazzerss.stocks.data.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object StockService {

    private val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fmpcloud.io/api/v3/")
            .build()
            .create(StockAPI::class.java)

    fun getInstance(): StockAPI {
        return retrofit
    }

}
