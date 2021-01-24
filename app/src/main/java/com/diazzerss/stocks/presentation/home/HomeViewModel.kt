package com.diazzerss.stocks.presentation.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.diazzerss.stocks.data.repository.StockRepositoryImpl
import com.diazzerss.stocks.domain.model.Stock
import com.diazzerss.stocks.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel @ViewModelInject constructor(private val repository: StockRepositoryImpl) : BaseViewModel() {

    private val _stockActive: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    val stockActive: LiveData<ArrayList<Stock>> = _stockActive
    private val _stockGainers: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    val stockGainers: LiveData<ArrayList<Stock>> = _stockGainers
    private val _stockLosers: MutableLiveData<ArrayList<Stock>> = MutableLiveData()
    val stockLosers: LiveData<ArrayList<Stock>> = _stockLosers


    init {
        loadStocks()
    }

    fun loadStocks() {
        viewModelScope.launch {
            progress.value = true
            launch(Dispatchers.IO) {
                try {
                    val activeStock = repository.getStockActive()
                    val gainersStock = repository.getStockGainers()
                    val losersStock = repository.getStockLosers()
                    _stockActive.postValue(activeStock)
                    _stockGainers.postValue(gainersStock)
                    _stockLosers.postValue(losersStock)
                } catch (ex: Throwable) {
                    progress.postValue(false)
                    error.postValue(true)
                    errorMessage.postValue(ex.message)
                }

            }.join()
            progress.value = false

        }

    }

}
