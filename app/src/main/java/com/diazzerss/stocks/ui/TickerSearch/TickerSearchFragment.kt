package com.diazzerss.stocks.ui.TickerSearch

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentTickerSearchBinding
import com.diazzerss.stocks.models.Ticker
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import kotlinx.android.synthetic.main.fragment_ticker_search.*

class TickerSearchFragment : Fragment() {
    private lateinit var tickerSearchViewModel: TickerSearchViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var errorView: LinearLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var refresher: SwipeRefreshLayout
    private var tickersAdapter: TickersAdapter = TickersAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTickerSearchBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Toolbar
        toolbar_tickerSearch.apply {
            (activity as AppCompatActivity).setSupportActionBar(this)
        }
        //ProgressBar
        progressBar = progressBar_tickerSearch.apply { isVisible = false }

        errorView = errorView_ticker.apply { isVisible = false }

        //RecyclerView
        recyclerView = rv_ticker.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tickersAdapter
        }
        //Refresher
        refresher = swipe_container_ticker.apply {
            setColorSchemeResources(R.color.colorAccent)
            setOnRefreshListener {
                refreshTickers()
                refresher.isRefreshing = false
            }
        }

        tickersAdapter.onItemClick = { ticker ->
            Navigation
                .findNavController(view)
                .navigate(R.id.navigation_company_profile, bundleOf("symbol" to ticker.symbol))
        }

        initViewModel()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_app_bar, menu)
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        tickerSearchViewModel.loadTicker(searchView.queryTextChanges())
    }

    private fun initViewModel() {
        tickerSearchViewModel = ViewModelProvider(this).get(TickerSearchViewModel::class.java)

        tickerSearchViewModel.tickers.observe(viewLifecycleOwner, Observer {
            showTickers(it)
        })

        tickerSearchViewModel.progress.observe(viewLifecycleOwner, Observer {
            showProgress(it)
        })
        tickerSearchViewModel.error.observe(viewLifecycleOwner, Observer {
            showError(it)
        })


    }

    private fun showTickers(tickers: ArrayList<Ticker>) {
        tickersAdapter.addData(tickers)
    }

    private fun showProgress(progress: Boolean) {
        progressBar.isVisible = progress
    }

    private fun showError(error: Boolean) {
        errorView.isVisible = error
    }

    private fun refreshTickers() {
        //TODO Fix refresh error
        refresher.setOnRefreshListener {
            tickerSearchViewModel.tickers.observe(viewLifecycleOwner, Observer {
                tickersAdapter.clearData()
                tickersAdapter.addData(it)
            })
        }
    }
}




