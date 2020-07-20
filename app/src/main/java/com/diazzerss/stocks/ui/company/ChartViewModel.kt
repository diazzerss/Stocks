package com.diazzerss.stocks.ui.company

import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.Graph
import com.diazzerss.stocks.repository.ChartRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChartViewModel : BaseViewModel() {
    private val graphicsRepository = ChartRepository
    val graphicsLiveData: MutableLiveData<ArrayList<Graph>> = MutableLiveData()
    private val graphList = ArrayList<Graph>()

    fun getChartData(ticker: String) {
        compositeDisposable.add(
            graphicsRepository.getChartData1Hour(ticker)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { Observable.fromIterable(it) }
                .take(7)
                .map{
                        Graph(it.date, it.close.toFloat())
                }
                .subscribe({
                    graphList.add(it)
                    graphicsLiveData.postValue(graphList)
                },
                    {

                    })
        )
    }
}
