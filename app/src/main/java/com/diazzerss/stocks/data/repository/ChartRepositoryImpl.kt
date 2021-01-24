package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockApi
import com.diazzerss.stocks.domain.model.Graph
import com.diazzerss.stocks.domain.repository.ChartRepository
import io.reactivex.Observable
import javax.inject.Inject

class ChartRepositoryImpl @Inject constructor() : ChartRepository {

    @Inject
    lateinit var stockApi: StockApi

    override fun getChartData1Hour(ticker: String): Observable<ArrayList<Graph>> {
        return stockApi.getChartData1Hour(ticker)
    }
}