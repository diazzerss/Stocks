package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockApi
import com.diazzerss.stocks.domain.model.CompanyProfile
import com.diazzerss.stocks.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {

    @Inject
    lateinit var stockApi: StockApi

    override suspend fun getProfile(ticker: String): ArrayList<CompanyProfile> {
        return stockApi.getCompanyProfile(ticker)
    }
}