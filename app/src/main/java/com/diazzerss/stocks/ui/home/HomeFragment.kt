package com.diazzerss.stocks.ui.home

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.diazzerss.stocks.BaseFragment
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentHomeBinding
import com.diazzerss.stocks.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment() {

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

        home_toolbar.apply {
            (activity as AppCompatActivity).setSupportActionBar(this)
        }

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

        stockActiveAdapter.setHeader("Топ активных")
        stockActiveAdapter.onItemClick = { stock ->
            Navigation
                .findNavController(view)
                .navigate(
                    R.id.navigation_company_profile,
                    bundleOf("ticker" to stock.ticker, "name" to stock.companyName)
                )
        }
        stockGainersAdapter.setHeader("Лидеры роста")
        stockGainersAdapter.onItemClick = { stock ->
            Navigation
                .findNavController(view)
                .navigate(
                    R.id.navigation_company_profile,
                    bundleOf("ticker" to stock.ticker, "name" to stock.companyName)
                )
        }
        stockLosersAdapter.setHeader("Лидеры падения")
        stockLosersAdapter.onItemClick = { stock ->
            Navigation
                .findNavController(view)
                .navigate(
                    R.id.navigation_company_profile,
                    bundleOf("ticker" to stock.ticker, "name" to stock.companyName)
                )
        }

        home_refreshContainer.apply {
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener {
                stockActiveAdapter.clearData()
                stockGainersAdapter.clearData()
                stockLosersAdapter.clearData()
                initViewModel()

            }

            initViewModel()


        }


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
            showProgress(it)
        })
        vm.error.observe(viewLifecycleOwner, Observer {
            //showError(it)
        })


    }

    override fun showContent() {
        super.showContent()
    }

    override fun showProgress(progress: Boolean) {
        super.showProgress(progress)
        home_refreshContainer.isRefreshing = progress
    }

    override fun showError(error: Boolean) {
        super.showError(error)
    }
}
