package com.diazzerss.stocks.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.Ticker
import com.diazzerss.stocks.repository.TickerRepository
import com.jakewharton.rxbinding3.InitialValueObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class SearchViewModel : BaseViewModel() {

    private val _ticker: MutableLiveData<ArrayList<Ticker>> = MutableLiveData()
    val ticker: LiveData<ArrayList<Ticker>> = _ticker

    private val repository: TickerRepository = TickerRepository

    fun loadTicker(observable: InitialValueObservable<CharSequence>) {

        compositeDisposable.add(observable
            .map { charSequence -> charSequence.toString() }
            .map { text -> text.toLowerCase(Locale.getDefault()).trim() }
            .filter { text -> text.length >= 2 }
            .debounce(1000, TimeUnit.MILLISECONDS)
            .filter { text -> text.isNotBlank() }
            .subscribe { query ->
                repository.getTicker(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        error.postValue(false)
                        progress.postValue(true)
                    }
                    .doAfterTerminate { progress.postValue(false) }
                    .subscribe({
                        error.postValue(false)
                        _ticker.postValue(it)
                    },
                        {

                        })
            })


    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()

    }
}