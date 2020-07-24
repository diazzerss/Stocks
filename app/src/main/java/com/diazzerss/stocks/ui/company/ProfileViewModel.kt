package com.diazzerss.stocks.ui.company

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.CompanyProfile
import com.diazzerss.stocks.repository.ProfileRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel : BaseViewModel() {

    private val profileRepository = ProfileRepository
    val profileLiveData: MutableLiveData<ArrayList<CompanyProfile>> = MutableLiveData()

    fun getProfile(ticker: String) {
        compositeDisposable.add(
            profileRepository.getProfile(ticker)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progress.postValue(true) }
                .doAfterTerminate { progress.postValue(false) }
                .subscribe({
                    profileLiveData.postValue(it)
                },
                    {

                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()

    }
}
