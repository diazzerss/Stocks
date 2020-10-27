package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockService
import com.diazzerss.stocks.domain.model.CompanyProfile
import com.diazzerss.stocks.domain.repository.ProfileRepository

class ProfileRepositoryImpl : ProfileRepository {

    override suspend fun getProfile(ticker: String): ArrayList<CompanyProfile> {
        return StockService
                .instance
                .getCompanyProfile(ticker)
    }
}