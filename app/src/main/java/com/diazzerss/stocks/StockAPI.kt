package com.diazzerss.stocks

import com.diazzerss.stocks.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**For testing purposes only*/
private const val key = "1745d59c7bab90cc2f39ff045df998e6"

interface StockAPI {

    @GET("search?limit=10&apikey=$key")
    fun getTicker(@Query("query") query: String): Single<ArrayList<Ticker>>

    @GET("profile/{ticker}?apikey=$key")
    fun getCompanyProfile(@Path("ticker") ticker: String): Single<ArrayList<CompanyProfile>>

    @GET("quote/{ticker}?apikey=$key")
    fun getQuote(@Path("ticker") ticker: String): Single<ArrayList<Quote>>

    @GET("actives?apikey=$key")
    fun getTopActive(): Single<ArrayList<Stock>>

    @GET("gainers?apikey=$key")
    fun getTopGainers(): Single<ArrayList<Stock>>

    @GET("losers?apikey=$key")
    fun getTopLosers(): Single<ArrayList<Stock>>

    @GET("historical-chart/15min/{ticker}?apikey=$key")
    fun getChartData1Hour(@Path("ticker") ticker: String): Observable<ArrayList<Graph>>

}