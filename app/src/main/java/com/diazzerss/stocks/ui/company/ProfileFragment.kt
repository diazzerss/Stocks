package com.diazzerss.stocks.ui.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diazzerss.stocks.databinding.FragmentProfileBinding
import com.diazzerss.stocks.utils.addNullPlaceholder
import com.diazzerss.stocks.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    private val vm by lazy { getViewModel<ProfileViewModel>() }

    lateinit var ticker: String

    companion object {
        fun newInstance(ticker: String) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString("ticker", ticker)
            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ticker = arguments?.getString("ticker").toString()

        vm.getProfile(ticker)


        return FragmentProfileBinding.inflate(inflater, container, false).root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO Fix ScrollView Collapsing
        val name = view.tv_profile_name
        val website = view.tv_profile_website
        val exchange = view.tv_profile_exchange
        val industry = view.tv_profile_industry
        val sector = view.tv_profile_sector
        val description = view.tv_profile_description
        vm.profileLiveData.observe(viewLifecycleOwner, Observer {
            name.text = it[0].companyName.addNullPlaceholder()
            website.text = it[0].website.addNullPlaceholder()
            exchange.text = it[0].exchange.addNullPlaceholder()
            industry.text = it[0].industry.addNullPlaceholder()
            sector.text = it[0].sector.addNullPlaceholder()
            description.text = it[0].description.addNullPlaceholder()
        })


    }

}
