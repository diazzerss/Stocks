package com.diazzerss.stocks.ui.home

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentHomeBinding
import com.diazzerss.stocks.model.Stock
import com.diazzerss.stocks.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private val vm by lazy { getViewModel<HomeViewModel>() }

    private var stockActiveAdapter = StockAdapter()
    private var stockGainersAdapter = StockAdapter()
    private var stockLosersAdapter = StockAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHomeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(home_toolbar)

        stockActiveAdapter.apply {
            setHeader("Топ активных")
            onItemClick = { openStockProfile(it) }
        }
        stockGainersAdapter.apply {
            setHeader("Лидеры роста")
            onItemClick = { openStockProfile(it) }
        }
        stockLosersAdapter.apply {
            setHeader("Лидеры падения")
            onItemClick = { openStockProfile(it) }
        }

        home_refreshContainer.apply {
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener {
                initViewModel()
            }
        }

        initRecyclerView()
        initViewModel()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
        menu.findItem(R.id.home_search).apply {
            setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    return false
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    return false
                }
            })
            actionView as SearchView
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_search -> {
                Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_navigation_home_to_navigation_search_old)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun initRecyclerView() {
        rv_stock_active.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stockActiveAdapter
            isNestedScrollingEnabled = false
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
        rv_stock_gainers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stockGainersAdapter
            isNestedScrollingEnabled = false
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
        rv_stock_losers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stockLosersAdapter
            isNestedScrollingEnabled = false
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }


    private fun openStockProfile(stock: Stock) {
        Navigation
            .findNavController(requireActivity(), R.id.nav_host_fragment)
            .navigate(
                R.id.navigation_company_profile,
                bundleOf("ticker" to stock.ticker, "name" to stock.companyName)
            )
    }


    private fun initViewModel() {

        vm.loadStockActive()
        vm.loadStockGainers()
        vm.loadStockLosers()

        vm.stockActive.observe(viewLifecycleOwner, Observer {
            stockActiveAdapter.addData(it)
        })
        vm.stockGainers.observe(viewLifecycleOwner, Observer {
            stockGainersAdapter.addData(it)
        })
        vm.stockLosers.observe(viewLifecycleOwner, Observer {
            stockLosersAdapter.addData(it)
        })
        vm.progress.observe(viewLifecycleOwner, Observer {
            home_content.isVisible = !it
            home_refreshContainer.isRefreshing = it
        })
        vm.error.observe(viewLifecycleOwner, Observer {
            //showError(it)
        })

    }


}
