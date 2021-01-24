package com.diazzerss.stocks.presentation.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.diazzerss.stocks.data.repository.NewsRepositoryImpl
import com.diazzerss.stocks.domain.model.Article
import com.diazzerss.stocks.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(private val newsRepositoryImpl: NewsRepositoryImpl) : BaseViewModel() {
    private val _article: MutableLiveData<ArrayList<Article>> = MutableLiveData()
    val article: LiveData<ArrayList<Article>> = _article

    init {
        getArticles()
    }

    fun getArticles() {

        viewModelScope.launch {
            progress.value = true
            launch(Dispatchers.IO) {
                try {
                    val articles = newsRepositoryImpl.getNews().articles
                    _article.postValue(articles)
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