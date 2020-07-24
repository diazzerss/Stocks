package com.diazzerss.stocks

import com.diazzerss.stocks.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**For testing purposes only*/
private const val key = "0efa8c0367e6946ebc8a8c5833f5f775"

interface StockAPI {

    @GET("search?limit=10&apikey=$key")
    fun getTicker(@Query("query") query: String): Single<ArrayList<Ticker>>

    @GET("profile/{ticker}?apikey=$key")
    fun getCompanyProfile(@Path("ticker") ticker: String): Observable<ArrayList<CompanyProfile>>

    @GET("quote/{ticker}?apikey=$key")
    fun getQuote(@Path("ticker") ticker: String): Observable<ArrayList<Quote>>

    @GET("actives?apikey=$key")
    fun getTopActive(): Observable<ArrayList<Stock>>

    @GET("gainers?apikey=$key")
    fun getTopGainers(): Observable<ArrayList<Stock>>

    @GET("losers?apikey=$key")
    fun getTopLosers(): Observable<ArrayList<Stock>>

    @GET("historical-chart/15min/{ticker}?apikey=$key")
    fun getChartData1Hour(@Path("ticker") ticker: String): Observable<ArrayList<Graph>>

}