package com.diazzerss.stocks.domain.model

data class Ticker (
    val symbol : String,
    val name : String?,
    val exchangeShortName: String?
)