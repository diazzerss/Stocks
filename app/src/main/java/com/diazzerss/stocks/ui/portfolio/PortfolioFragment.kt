package com.diazzerss.stocks.ui.portfolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diazzerss.stocks.databinding.FragmentPortfolioBinding
import com.diazzerss.stocks.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_portfolio.*

class PortfolioFragment : Fragment() {

    private val vm by lazy { getViewModel<PortfolioViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentPortfolioBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = text_portfolio
        vm.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }
}
