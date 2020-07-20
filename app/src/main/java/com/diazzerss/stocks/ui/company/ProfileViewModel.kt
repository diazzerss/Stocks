package com.diazzerss.stocks.ui.company

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diazzerss.stocks.model.CompanyProfile
import com.diazzerss.stocks.repository.ProfileRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel : ViewModel() {

    private val profileRepository = ProfileRepository
    val profileLiveData: MutableLiveData<ArrayList<CompanyProfile>> = MutableLiveData()

    fun getProfile(ticker:String) {
        profileRepository.getProfile(ticker)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                profileLiveData.postValue(it)
            },
                {

                })
    }
}
