package com.diazzerss.stocks.model

data class Graph(
    val date : String,
    val open : Double,
    val low : Double,
    val high : Double,
    val close : Double,
    val volume : Int
)