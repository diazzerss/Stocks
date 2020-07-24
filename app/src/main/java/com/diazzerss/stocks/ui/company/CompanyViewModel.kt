package com.diazzerss.stocks.ui.company

import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.Quote
import com.diazzerss.stocks.repository.QuoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CompanyViewModel : BaseViewModel() {
    val quote: MutableLiveData<ArrayList<Quote>> = MutableLiveData()
    private val repository: QuoteRepository = QuoteRepository

    fun loadQuote(ticker: String) {
        compositeDisposable.add(
            repository.getQuote(ticker)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    quote.postValue(it)
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