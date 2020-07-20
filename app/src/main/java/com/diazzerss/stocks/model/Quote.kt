package com.diazzerss.stocks.model

data class Quote(
    val symbol: String,
    val name: String,
    val price: Double,
    val changesPercentage: Double,
    val change: Double,
    val dayLow: Double,
    val dayHigh: Double,
    val yearHigh: Double,
    val yearLow: Double,
    val marketCap: Double,
    val priceAvg50: Double,
    val priceAvg200: Double,
    val volume: Int,
    val avgVolume: Int,
    val exchange: String,
    val open: Double,
    val previousClose: Double,
    val eps: Double,
    val pe: Double,
    val earningsAnnouncement: String,
    val sharesOutstanding: Int,
    val timestamp: Int

)