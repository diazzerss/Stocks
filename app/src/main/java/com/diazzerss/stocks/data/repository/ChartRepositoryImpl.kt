package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockApiInterface
import com.diazzerss.stocks.domain.model.Graph
import com.diazzerss.stocks.domain.repository.ChartRepository
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject

class ChartRepositoryImpl : ChartRepository, KoinComponent {

    private val stockServiceClient: StockApiInterface by inject()

    override fun getChartData1Hour(ticker: String): Observable<ArrayList<Graph>> {
        return stockServiceClient.getChartData1Hour(ticker)
    }
}