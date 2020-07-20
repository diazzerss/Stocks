package com.diazzerss.stocks.model

data class Stock(
    val companyName: String,
    val ticker: String,
    val price: Double,
    val changes: Double,
    val changesPercentage: String
)