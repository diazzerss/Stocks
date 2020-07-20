package com.diazzerss.stocks

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {


    open fun showContent() {

    }

    open fun showProgress(progress: Boolean) {

    }

    open fun showError(error: Boolean) {

    }
}