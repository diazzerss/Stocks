package com.diazzerss.stocks.repository

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.model.GraphData
import io.reactivex.Observable

object ChartRepository {
    fun getChartData1Hour(ticker:String): Observable<ArrayList<GraphData>> {
        return StockService
            .create()
            .getChartData1Hour(ticker)
    }
}