package com.diazzerss.stocks.ui.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diazzerss.stocks.BaseViewModel
import com.diazzerss.stocks.model.CompanyProfile
import com.diazzerss.stocks.repository.ProfileRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel : BaseViewModel() {

    private val profileRepository = ProfileRepository
    private val _profile: MutableLiveData<ArrayList<CompanyProfile>> = MutableLiveData()
    val profile: LiveData<ArrayList<CompanyProfile>> = _profile

    fun getProfile(ticker: String) {
        compositeDisposable.add(
            profileRepository.getProfile(ticker)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progress.postValue(true) }
                .doAfterTerminate { progress.postValue(false) }
                .subscribe({
                    _profile.postValue(it)
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
