package com.diazzerss.stocks

import com.diazzerss.stocks.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StockService {

    @GET("search?limit=10&apikey=552b5818a1cd3b17126bc16c82be92e6")
    fun getTicker(@Query("query") query: String): Single<ArrayList<Ticker>>

    @GET("profile/{ticker}?apikey=552b5818a1cd3b17126bc16c82be92e6")
    fun getCompanyProfile(@Path("ticker") ticker: String): Observable<ArrayList<CompanyProfile>>

    @GET("quote/{ticker}?apikey=552b5818a1cd3b17126bc16c82be92e6")
    fun getQuote(@Path("ticker") ticker: String): Observable<ArrayList<Quote>>

    @GET("actives?apikey=552b5818a1cd3b17126bc16c82be92e6")
    fun getTopActive(): Observable<ArrayList<Stock>>

    @GET("gainers?apikey=552b5818a1cd3b17126bc16c82be92e6")
    fun getTopGainers(): Observable<ArrayList<Stock>>

    @GET("losers?apikey=552b5818a1cd3b17126bc16c82be92e6")
    fun getTopLosers(): Observable<ArrayList<Stock>>

    @GET("historical-chart/1hour/{ticker}?apikey=552b5818a1cd3b17126bc16c82be92e6")
    fun getChartData1Hour(@Path("ticker") ticker: String): Observable<ArrayList<GraphData>>

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