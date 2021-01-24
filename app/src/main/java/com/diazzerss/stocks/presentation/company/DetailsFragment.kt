package com.diazzerss.stocks.presentation.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diazzerss.stocks.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val vm: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDetails.apply {
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
