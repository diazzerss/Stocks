package com.diazzerss.stocks.domain.model

import java.math.BigDecimal

data class Stock(
    val companyName: String,
    val ticker: String,
    val price: BigDecimal,
    val changes: BigDecimal,
    val changesPercentage: String
)