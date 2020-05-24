package com.diazzerss.stocks.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.stock_active.*
import kotlinx.android.synthetic.main.stock_gainers.*
import kotlinx.android.synthetic.main.stock_losers.*


class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var rvActive: RecyclerView
    private lateinit var rvGainers: RecyclerView
    private lateinit var rvLosers: RecyclerView

    private var stockActiveAdapter: StocksAdapter = StocksAdapter()
    private var stockGainersAdapter: StocksAdapter = StocksAdapter()
    private var stockLosersAdapter: StocksAdapter = StocksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return FragmentHomeBinding.inflate(inflater, container, false).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stockActiveAdapter.onItemClick = { stock ->
            Navigation
                .findNavController(view)
                .navigate(R.id.navigation_company_profile, bundleOf("ticker" to stock.ticker, "name" to stock.companyName))
        }
        stockGainersAdapter.onItemClick = { stock ->
            Navigation
                .findNavController(view)
                .navigate(R.id.navigation_company_profile, bundleOf("ticker" to stock.ticker, "name" to stock.companyName))
        }
        stockLosersAdapter.onItemClick = { stock ->
            Navigation
                .findNavController(view)
                .navigate(R.id.navigation_company_profile, bundleOf("ticker" to stock.ticker, "name" to stock.companyName))
        }

        rvActive = rv_stock_active.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stockActiveAdapter
            isNestedScrollingEnabled = false
        }
        rvGainers = rv_stock_gainers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stockGainersAdapter
            isNestedScrollingEnabled = false
        }
        rvLosers = rv_stock_losers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stockLosersAdapter
            isNestedScrollingEnabled = false
        }



        initViewModel()

    }


    private fun initViewModel() {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        homeViewModel.loadStockActive()
        homeViewModel.loadStockGainers()
        homeViewModel.loadStockLosers()

        homeViewModel.stockActive.observe(viewLifecycleOwner, Observer {
            stockActiveAdapter.addData(it)
        })

        homeViewModel.stockGainers.observe(viewLifecycleOwner, Observer {
            stockGainersAdapter.addData(it)
        })

        homeViewModel.stockLosers.observe(viewLifecycleOwner, Observer {
            stockLosersAdapter.addData(it)
        })


    }

}
