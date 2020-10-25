package com.diazzerss.stocks.presentation.company

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentCompanyBinding
import com.diazzerss.stocks.utils.addSign
import com.diazzerss.stocks.utils.getViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_company.*


class CompanyFragment : Fragment() {

    private val ticker by lazy { arguments?.getString("ticker").toString() }
    private val name by lazy { arguments?.getString("name").toString() }

    private val vm by lazy { getViewModel<CompanyViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return FragmentCompanyBinding.inflate(inflater, container, false).root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        company_toolbar.apply {
            title = name
            subtitle = ticker
            (activity as AppCompatActivity).setSupportActionBar(this)
            (activity as AppCompatActivity).supportActionBar?.apply {
                elevation = 0f
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }

        company_viewpager.adapter = PagerAdapter(this)
        company_viewpager.offscreenPageLimit = 2
        TabLayoutMediator(company_tabs, company_viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = "Профиль"
                1 -> tab.text = "Детали"
                2 -> tab.text = "График"
            }
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.company_menu, menu)
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
        vm.loadQuote(ticker)
        vm.quote.observe(viewLifecycleOwner, Observer {
            tv_company_price.text = it[0].price.toString().plus("$")
            tv_company_change.text = it[0].change.addSign()
            tv_company_changesPercentage.text =
                " (".plus(it[0].changesPercentage.toString()).plus("%)")

            if (it[0].change > 0) {
                tv_company_change.setTextColor(Color.parseColor("#4CAF50"))
                tv_company_changesPercentage.setTextColor(Color.parseColor("#4CAF50"))
            } else {
                tv_company_change.setTextColor(Color.parseColor("#F44336"))
                tv_company_changesPercentage.setTextColor(Color.parseColor("#F44336"))
            }
        })
    }

    inner class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {

            return when (position) {
                0 -> ProfileFragment.newInstance(ticker)
                1 -> DetailsFragment.newInstance(ticker)
                else -> return ChartFragment.newInstance(ticker)
            }
        }

    }

}


