package com.diazzerss.stocks.utils

import java.text.DecimalFormat

/**
 * Created by Diaz on 07.05.2020.
 */

fun Double.addSign(): String {
    return if (this > 0) "$+".plus(DecimalFormat("#0.00").format(this))
    else "$".plus(DecimalFormat("#0.00").format(this))
}

fun String.removeBrackets(): String {
    return this.removePrefix("(").removeSuffix(")")
}

fun String.toInitials(): String {
    return this.trim().getOrNull(0)?.toUpperCase().toString()
}

fun Double.formatDouble():String {
    return "$".plus(DecimalFormat("#0.00").format(this))
}






