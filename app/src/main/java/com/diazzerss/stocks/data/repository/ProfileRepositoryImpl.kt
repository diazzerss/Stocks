package com.diazzerss.stocks.data.repository

import com.diazzerss.stocks.data.api.StockApiInterface
import com.diazzerss.stocks.domain.model.CompanyProfile
import com.diazzerss.stocks.domain.repository.ProfileRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProfileRepositoryImpl : ProfileRepository, KoinComponent {

    private val stockServiceClient: StockApiInterface by inject()

    override suspend fun getProfile(ticker: String): ArrayList<CompanyProfile> {
        return stockServiceClient.getCompanyProfile(ticker)
    }
}