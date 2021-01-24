package com.diazzerss.stocks.presentation.company

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.diazzerss.stocks.data.repository.ProfileRepositoryImpl
import com.diazzerss.stocks.domain.model.CompanyProfile
import com.diazzerss.stocks.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(private val profileRepositoryImpl: ProfileRepositoryImpl) : BaseViewModel() {

    private val _profile: MutableLiveData<ArrayList<CompanyProfile>> = MutableLiveData()
    val profile: LiveData<ArrayList<CompanyProfile>> = _profile

    fun getProfile(ticker: String) {

        viewModelScope.launch {
            progress.value = true
            launch(Dispatchers.IO) {
                try {
                    val profile = profileRepositoryImpl.getProfile(ticker)
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
