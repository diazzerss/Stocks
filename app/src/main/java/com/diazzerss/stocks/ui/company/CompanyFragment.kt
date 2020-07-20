package com.diazzerss.stocks.ui.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.diazzerss.stocks.databinding.FragmentCompanyBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_company.*


class CompanyFragment : Fragment() {

    private lateinit var ticker: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return FragmentCompanyBinding.inflate(inflater, container, false).root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ticker = arguments?.getString("ticker").toString()

        toolbar_company.apply {
            title = ticker
            (activity as AppCompatActivity).setSupportActionBar(this)
        }

        viewpager_company.adapter = PagerAdapter(this)
        TabLayoutMediator(tabs_company, viewpager_company) { tab, position ->
            when (position) {
                0 -> tab.text = "Профиль"
                1 -> tab.text = "Детали"
                2 -> tab.text = "График"
            }
        }.attach()

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


