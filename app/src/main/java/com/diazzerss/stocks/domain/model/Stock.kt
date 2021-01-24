package com.diazzerss.stocks.domain.model

import java.math.BigDecimal

data class Stock(
        val ticker: String,
        val companyName: String,
        val price: BigDecimal,
        val changes: BigDecimal,
        val changesPercentage: String
)