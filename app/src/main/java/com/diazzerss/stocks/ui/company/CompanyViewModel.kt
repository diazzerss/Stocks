package com.diazzerss.stocks.ui.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.Quote
import com.diazzerss.stocks.repository.QuoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CompanyViewModel : BaseViewModel() {
    private val _quote: MutableLiveData<ArrayList<Quote>> = MutableLiveData()
    val quote: LiveData<ArrayList<Quote>> = _quote
    val repository: QuoteRepository = QuoteRepository

    fun loadQuote(ticker: String) {
        compositeDisposable.add(
            repository.getQuote(ticker)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _quote.postValue(it)
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