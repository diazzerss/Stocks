package com.diazzerss.stocks.presentation.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.diazzerss.stocks.presentation.BaseViewModel
import com.diazzerss.stocks.data.repository.QuoteRepositoryImpl
import com.diazzerss.stocks.domain.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyViewModel : BaseViewModel() {
    private val _quote: MutableLiveData<ArrayList<Quote>> = MutableLiveData()
    val quote: LiveData<ArrayList<Quote>> = _quote
    val repository: QuoteRepositoryImpl = QuoteRepositoryImpl()

    fun loadQuote(ticker: String) {

        viewModelScope.launch {
            progress.value = true
            launch(Dispatchers.IO) {
                try {
                    val quote = repository.getQuote(ticker)
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