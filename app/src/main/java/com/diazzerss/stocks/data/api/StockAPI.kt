package com.diazzerss.stocks.data.api

import com.diazzerss.stocks.domain.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface StockAPI {

    @GET("search?limit=10&apikey=eae36d88054b1fcf0f6d4e3f20b7aa7b")
    fun getTicker(@Query("query") query: String): Single<ArrayList<Ticker>>

    @GET("profile/{ticker}?apikey=eae36d88054b1fcf0f6d4e3f20b7aa7b")
    suspend fun getCompanyProfile(@Path("ticker") ticker: String): ArrayList<CompanyProfile>

    @GET("quote/{ticker}?apikey=eae36d88054b1fcf0f6d4e3f20b7aa7b")
    suspend fun getQuote(@Path("ticker") ticker: String): ArrayList<Quote>

    @GET("actives?apikey=eae36d88054b1fcf0f6d4e3f20b7aa7b")
    suspend fun getTopActive(): ArrayList<Stock>

    @GET("gainers?apikey=eae36d88054b1fcf0f6d4e3f20b7aa7b")
    suspend fun getTopGainers(): ArrayList<Stock>

    @GET("losers?apikey=eae36d88054b1fcf0f6d4e3f20b7aa7b")
    suspend fun getTopLosers(): ArrayList<Stock>

    @GET("historical-chart/15min/{ticker}?apikey=eae36d88054b1fcf0f6d4e3f20b7aa7b")
    fun getChartData1Hour(@Path("ticker") ticker: String): Observable<ArrayList<Graph>>

}