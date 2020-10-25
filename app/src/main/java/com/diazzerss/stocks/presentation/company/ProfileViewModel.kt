package com.diazzerss.stocks.presentation.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.diazzerss.stocks.presentation.BaseViewModel
import com.diazzerss.stocks.domain.model.CompanyProfile
import com.diazzerss.stocks.data.repository.ProfileRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : BaseViewModel() {

    private val repository = ProfileRepositoryImpl()
    private val _profile: MutableLiveData<ArrayList<CompanyProfile>> = MutableLiveData()
    val profile: LiveData<ArrayList<CompanyProfile>> = _profile

    fun getProfile(ticker: String) {

        viewModelScope.launch {
            progress.value = true
            launch(Dispatchers.IO) {
                try {
                    val profile = repository.getProfile(ticker)
                    _profile.postValue(profile)
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
