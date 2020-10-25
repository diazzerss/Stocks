package com.diazzerss.stocks.domain.repository

import com.diazzerss.stocks.domain.model.Graph
import io.reactivex.Observable

interface ChartRepository {
    fun getChartData1Hour(ticker: String): Observable<ArrayList<Graph>>
}