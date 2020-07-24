package com.diazzerss.stocks.repository

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.model.CompanyProfile
import io.reactivex.Observable

object ProfileRepository {

    fun getProfile(ticker:String): Observable<ArrayList<CompanyProfile>> {
        return StockService
            .getInstance()
            .getCompanyProfile(ticker)
    }
}