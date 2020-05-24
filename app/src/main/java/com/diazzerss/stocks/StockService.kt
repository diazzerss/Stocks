package com.diazzerss.stocks

import com.diazzerss.stocks.models.CompanyProfile
import com.diazzerss.stocks.models.Stock
import com.diazzerss.stocks.models.Ticker
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface StockService {

    @GET("search?limit=15&apikey=63bba40cd270c6d62fe2d8a4a16f3f82")
    //TODO change limit
    fun getTicker(@Query("query") query:String):  Single<ArrayList<Ticker>>

    @GET("profile/{ticker}?apikey=63bba40cd270c6d62fe2d8a4a16f3f82")
    fun getCompanyProfile(@Path("ticker") ticker:String): Observable <ArrayList<CompanyProfile>>

    @GET("actives?apikey=63bba40cd270c6d62fe2d8a4a16f3f82")
    fun getTopActive(): Observable <ArrayList<Stock>>

    @GET("gainers?apikey=63bba40cd270c6d62fe2d8a4a16f3f82")
    fun getTopGainers(): Observable <ArrayList<Stock>>

    @GET("losers?apikey=63bba40cd270c6d62fe2d8a4a16f3f82")
    fun getTopLosers(): Observable <ArrayList<Stock>>




    companion object Factory {
        fun create(): StockService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://fmpcloud.io/api/v3/")
                .build()

            return retrofit.create(StockService::class.java)
        }
    }
}