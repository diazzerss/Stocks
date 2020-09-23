package com.diazzerss.stocks.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.Article
import com.diazzerss.stocks.repository.ArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsViewModel : BaseViewModel() {
    private val _article: MutableLiveData<ArrayList<Article>> = MutableLiveData()
    val article: LiveData<ArrayList<Article>> = _article
    private val repository: ArticleRepository = ArticleRepository

    init {
        getArticles()
    }

    fun getArticles() {
        compositeDisposable.add(
            repository.getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    error.postValue(false)
                    progress.postValue(true)
                }
                .doAfterTerminate { progress.postValue(false) }
                .map {
                    it.articles
                }
                .subscribe({
                    error.postValue(false)
                    _article.postValue(it)
                }, {
                    it.printStackTrace()
                })
        )
    }
}