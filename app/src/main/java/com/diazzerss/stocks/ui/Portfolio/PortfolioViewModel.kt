package com.diazzerss.stocks.ui.Portfolio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PortfolioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is portfolio Fragment"
    }
    val text: LiveData<String> = _text
}