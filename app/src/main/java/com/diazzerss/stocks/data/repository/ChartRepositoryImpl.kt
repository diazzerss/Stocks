package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockService
import com.diazzerss.stocks.domain.model.Graph
import com.diazzerss.stocks.domain.repository.ChartRepository
import io.reactivex.Observable

class ChartRepositoryImpl : ChartRepository {
    override fun getChartData1Hour(ticker: String): Observable<ArrayList<Graph>> {
        return StockService
                .getInstance()
                .getChartData1Hour(ticker)
    }
}