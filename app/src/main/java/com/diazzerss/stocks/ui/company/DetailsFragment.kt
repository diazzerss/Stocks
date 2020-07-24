package com.diazzerss.stocks.ui.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diazzerss.stocks.databinding.FragmentDetailsBinding
import com.diazzerss.stocks.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private val vm by lazy { getViewModel<DetailsViewModel>() }
    private val ticker by lazy { arguments?.getString("ticker").toString() }
    private var quoteAdapter: QuoteAdapter = QuoteAdapter()

    companion object {
        fun newInstance(ticker: String) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putString("ticker", ticker)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initViewModel()
        return FragmentDetailsBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_details.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = quoteAdapter
        }

    }

    private fun initViewModel() {
        vm.loadQuote(ticker)
        vm.quote.observe(viewLifecycleOwner, Observer {
            quoteAdapter.addData(it)
        })

    }
}
