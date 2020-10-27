package com.diazzerss.stocks.presentation.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.data.repository.ChartRepositoryImpl
import com.diazzerss.stocks.domain.model.Graph
import com.diazzerss.stocks.presentation.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ChartViewModel : BaseViewModel() {
    private val graphicsRepository = ChartRepositoryImpl()
    private val graphList = ArrayList<Graph>()
    private val _graph: MutableLiveData<ArrayList<Graph>> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val graph: LiveData<ArrayList<Graph>> = _graph

    fun getChartData(ticker: String) {

        compositeDisposable.add(graphicsRepository.getChartData1Hour(ticker)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { Observable.fromIterable(it) }
                .take(27)
                .subscribe({
                    graphList.add(it)
                    _graph.postValue(graphList)
                },
                        {

                        }))

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
