package com.diazzerss.stocks.ui.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.Graph
import com.diazzerss.stocks.repository.ChartRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChartViewModel : BaseViewModel() {
    private val graphicsRepository = ChartRepository
    private val graphList = ArrayList<Graph>()
    private val _graph: MutableLiveData<ArrayList<Graph>> = MutableLiveData()
    val graph: LiveData<ArrayList<Graph>> = _graph

    fun getChartData(ticker: String) {
        compositeDisposable.add(
            graphicsRepository.getChartData1Hour(ticker)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { Observable.fromIterable(it) }
                .take(27)
                .subscribe({
                    graphList.add(it)
                    _graph.postValue(graphList)
                },
                    {

                    })
        )
    }
}
