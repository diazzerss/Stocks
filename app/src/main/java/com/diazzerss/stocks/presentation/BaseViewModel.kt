package com.diazzerss.stocks.presentation

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val progress: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }

}