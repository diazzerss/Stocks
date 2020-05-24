package com.diazzerss.stocks.models

/**
 * Created by Diaz on 06.05.2020.
 */

data class Stock(
    val companyName: String,
    val ticker: String,
    val price: Double,
    val changes: Double,
    val changesPercentage: String
)