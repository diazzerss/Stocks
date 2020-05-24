package com.diazzerss.stocks.ui.Company

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diazzerss.stocks.models.CompanyProfile
import com.diazzerss.stocks.repositories.ProfileRepository
import io.reactivex.Observable
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
