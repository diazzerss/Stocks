package com.diazzerss.stocks.ui.news

import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.Article
import com.diazzerss.stocks.repository.ArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsViewModel : BaseViewModel() {
    val article: MutableLiveData <ArrayList<Article>> = MutableLiveData()
    private val repository: ArticleRepository = ArticleRepository
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
                    article.postValue(it)
                }, {
                    it.printStackTrace()
                })
        )
    }
}