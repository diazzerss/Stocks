package com.diazzerss.stocks.repository

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.model.CompanyProfile
import io.reactivex.Observable
import io.reactivex.Single

object ProfileRepository {

    fun getProfile(ticker: String): Single<ArrayList<CompanyProfile>> {
        return StockService
            .getInstance()
            .getCompanyProfile(ticker)
    }
}