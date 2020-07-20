package com.diazzerss.stocks.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diazzerss.stocks.BaseViewModelFactory
import java.text.DecimalFormat

fun Double.addSign(): String {
    return if (this > 0) "+".plus((DecimalFormat("#0.00").format(this)).plus("$"))
    else (DecimalFormat("#0.00").format(this)).plus("$")
}

fun Double.formatDouble(): String {
    return (DecimalFormat("#0.00").format(this)).plus("$")
}

fun String?.addNullPlaceholder(): String {
    return if (this.isNullOrEmpty()) "null"
    else this
}


inline fun <reified T : ViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this,
            BaseViewModelFactory(creator)
        ).get(T::class.java)
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this,
            BaseViewModelFactory(creator)
        ).get(T::class.java)
}





