package com.diazzerss.stocks.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentHomeBinding
import com.diazzerss.stocks.domain.model.Stock
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val vm: HomeViewModel by viewModels()

    @Inject lateinit var stockActiveAdapter: StockAdapter
    @Inject lateinit var stockGainersAdapter: StockAdapter
    @Inject lateinit var stockLosersAdapter: StockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.homeToolbar)

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

        binding.homeRefreshContainer.apply {
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener {
                vm.loadStocks()
            }
        }
        //TODO Исправить
        initRecyclerView()
        bindViewModel()

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
        binding.rvStockActive.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stockActiveAdapter
            isNestedScrollingEnabled = false
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
        binding.rvStockGainers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stockGainersAdapter
            isNestedScrollingEnabled = false
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
        binding.rvStockLosers.apply {
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


    private fun bindViewModel() {

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
            binding.homeContent.isVisible = !it
            binding.homeRefreshContainer.isRefreshing = it
        })
        vm.error.observe(viewLifecycleOwner, Observer {
            //TODO
            //show error screen
        })
        vm.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.d("error", it.toString())
        })

    }


}
