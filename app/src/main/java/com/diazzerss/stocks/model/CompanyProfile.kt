package com.diazzerss.stocks.model

data class CompanyProfile(
    val symbol: String?,
    val price: Double?,
    val beta: Double?,
    val volAvg: Int?,
    val mktCap: Long?,
    val lastDiv: Double?,
    val range: String?,
    val changes: Double?,
    val companyName: String?,
    val exchange: String?,
    val industry: String?,
    val website: String?,
    val description: String?,
    val ceo: String?,
    val sector: String?,
    val dcfDiff: Double?,
    val dcf: Double?,
    val image: String?
)
