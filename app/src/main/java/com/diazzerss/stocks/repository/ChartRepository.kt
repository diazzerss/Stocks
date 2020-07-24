package com.diazzerss.stocks.repository

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.model.Graph
import io.reactivex.Observable

object ChartRepository {
    fun getChartData1Hour(ticker:String): Observable<ArrayList<Graph>> {
        return StockService
            .getInstance()
            .getChartData1Hour(ticker)
    }
}