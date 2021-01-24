package com.diazzerss.stocks.presentation.company

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.diazzerss.stocks.data.repository.QuoteRepositoryImpl
import com.diazzerss.stocks.domain.model.Quote
import com.diazzerss.stocks.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyViewModel @ViewModelInject constructor(private val quoteRepositoryImpl: QuoteRepositoryImpl) : BaseViewModel() {
    private val _quote: MutableLiveData<ArrayList<Quote>> = MutableLiveData()
    val quote: LiveData<ArrayList<Quote>> = _quote

    fun loadQuote(ticker: String) {

        viewModelScope.launch {
            progress.value = true
            launch(Dispatchers.IO) {
                try {
                    val quote = this@CompanyViewModel.quoteRepositoryImpl.getQuote(ticker)
                    _quote.postValue(quote)
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