package com.diazzerss.stocks.domain.repository

import com.diazzerss.stocks.domain.model.CompanyProfile

interface ProfileRepository {
    suspend fun getProfile(ticker: String): ArrayList<CompanyProfile>
}