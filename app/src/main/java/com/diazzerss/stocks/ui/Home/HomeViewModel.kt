package com.diazzerss.stocks.ui.Home

import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.models.Stock
import com.diazzerss.stocks.repositories.StocksRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : BaseViewModel() {

    val stockActive: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    val stockGainers: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    val stockLosers: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    private val repository: StocksRepository = StocksRepository

    fun loadStockActive() {
        compositeDisposable.add(
            repository.getStockActive()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
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
