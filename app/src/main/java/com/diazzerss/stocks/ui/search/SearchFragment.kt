package com.diazzerss.stocks.ui.search

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.diazzerss.stocks.BaseFragment
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentSearchBinding
import com.diazzerss.stocks.model.Ticker
import com.diazzerss.stocks.utils.getViewModel
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.search_refreshContainer
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragment : BaseFragment() {

    private val vm by lazy { getViewModel<SearchViewModel>() }

    private var tickerAdapter: TickerAdapter = TickerAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSearchBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Toolbar
        val toolbar = search_toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)


        search_rv_ticker.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tickerAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }

        search_refreshContainer.apply {
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener {
                refreshTickers()
                search_refreshContainer.isRefreshing = false
            }
        }

        tickerAdapter.onItemClick = { ticker ->
            Navigation
                .findNavController(view)
                .navigate(R.id.navigation_company_profile, bundleOf("ticker" to ticker.symbol))
        }

        initViewModel()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.isFocusable = true
        searchView.isIconified = false
        vm.loadTicker(searchView.queryTextChanges())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewModel() {

        vm.tickers.observe(viewLifecycleOwner, Observer {
            showTickers(it)
        })

        vm.progress.observe(viewLifecycleOwner, Observer {
            showProgress(it)
        })
        vm.error.observe(viewLifecycleOwner, Observer {
            showError(it)
        })


    }


    private fun showTickers(tickers:ArrayList<Ticker>){
        tickerAdapter.addData(tickers)
    }

    override fun showContent() {
        super.showContent()

    }

    override fun showProgress(progress: Boolean) {
        super.showProgress(progress)
        search_refreshContainer.isRefreshing = progress
    }

    override fun showError(error: Boolean) {
        super.showError(error)
    }



    private fun refreshTickers() {
        //TODO Fix refresh error
        search_refreshContainer.setOnRefreshListener {
            vm.tickers.observe(viewLifecycleOwner, Observer {
                tickerAdapter.clearData()
                tickerAdapter.addData(it)
            })
        }
    }
}




