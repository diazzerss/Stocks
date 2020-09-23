package com.diazzerss.stocks.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.Stock
import com.diazzerss.stocks.repository.StockRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : BaseViewModel() {

    private val repository: StockRepository = StockRepository

    private val _stockActive: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    val stockActive: LiveData<ArrayList<Stock>> = _stockActive
    private val _stockGainers: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    val stockGainers: LiveData<ArrayList<Stock>> = _stockGainers
    private val _stockLosers: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    val stockLosers: LiveData<ArrayList<Stock>> = _stockLosers


    init {
        loadStockActive()
        loadStockGainers()
        loadStockLosers()
    }

    fun loadStockActive() {
        compositeDisposable.add(
            repository.getStockActive()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    error.postValue(false)
                    progress.postValue(true)
                }
                .doAfterTerminate { progress.postValue(false) }
                .subscribe({
                    error.postValue(false)
                    _stockActive.postValue(it)
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
                    _stockGainers.postValue(it)
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
                    _stockLosers.postValue(it)
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
