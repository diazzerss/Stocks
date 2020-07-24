package com.diazzerss.stocks

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object StockService {

    fun getInstance(): StockAPI {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fmpcloud.io/api/v3/")
            .build()

        return retrofit.create(StockAPI::class.java)
    }
}
