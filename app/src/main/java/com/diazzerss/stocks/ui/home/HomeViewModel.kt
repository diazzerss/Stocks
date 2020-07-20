package com.diazzerss.stocks.ui.home

import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.Stock
import com.diazzerss.stocks.repository.StockRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : BaseViewModel() {

    val stockActive: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    val stockGainers: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    val stockLosers: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    private val repository: StockRepository = StockRepository

    fun loadStockActive() {
        compositeDisposable.add(
            repository.getStockActive()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    error.postValue(false)
                    progress.postValue(true) }
                .doAfterTerminate { progress.postValue(false) }
                .subscribe({
                    error.postValue(false)
                    stockActive.postValue(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun loadStockGainers() {
        compositeDisposable.add(
            repository.getStockGainers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    stockGainers.postValue(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun loadStockLosers() {
        compositeDisposable.add(
            repository.getStockLosers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    stockLosers.postValue(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()

    }
}
