package com.diazzerss.stocks.repositories

import com.diazzerss.stocks.StockService
import com.diazzerss.stocks.models.CompanyProfile
import io.reactivex.Observable

/**
 * Created by Diaz on 19.05.2020.
 */
object ProfileRepository {

    fun getProfile(ticker:String): Observable<ArrayList<CompanyProfile>> {
        return StockService
            .create()
            .getCompanyProfile(ticker)
    }
}