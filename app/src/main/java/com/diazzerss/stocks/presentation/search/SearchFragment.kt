package com.diazzerss.stocks.presentation.search

import android.os.Bundle
import android.view.*
import android.widget.AbsListView
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
import androidx.recyclerview.widget.RecyclerView
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentSearchBinding
import com.diazzerss.stocks.utils.getViewModel
import com.diazzerss.stocks.utils.hideKeyboard
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

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

        (activity as AppCompatActivity).setSupportActionBar(search_toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)


        search_rv_ticker.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tickerAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        hideKeyboard()
                    }
                }
            })


        }

        search_refreshContainer.apply {
            setColorSchemeResources(R.color.colorPrimary)
            isEnabled = false
        }

        tickerAdapter.onItemClick = { ticker ->
            hideKeyboard()
            Navigation
                .findNavController(view)
                .navigate(
                    R.id.navigation_company_profile,
                    bundleOf("ticker" to ticker.symbol, "name" to ticker.name)
                )
        }

        bindViewModel()

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

    private fun bindViewModel() {

        vm.ticker.observe(viewLifecycleOwner, Observer {
            tickerAdapter.addData(it)
        })

        vm.progress.observe(viewLifecycleOwner, Observer {
            search_refreshContainer.isRefreshing = it
            search_rv_ticker.isVisible = !it

        })
        vm.error.observe(viewLifecycleOwner, Observer {
            //showError(it)
        })


    }

}




