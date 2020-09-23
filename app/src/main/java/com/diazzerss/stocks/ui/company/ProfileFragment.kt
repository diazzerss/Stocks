package com.diazzerss.stocks.ui.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentProfileBinding
import com.diazzerss.stocks.utils.addNoDataPlaceholder
import com.diazzerss.stocks.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private val vm by lazy { getViewModel<ProfileViewModel>() }
    private val ticker by lazy { arguments?.getString("ticker").toString() }

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

        vm.getProfile(ticker)

        return FragmentProfileBinding.inflate(inflater, container, false).root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
    }

    private fun bindViewModel() {
        vm.profile.observe(viewLifecycleOwner, Observer {
            tv_profile_ceo.text = it[0].ceo.addNoDataPlaceholder()
            tv_profile_address.text = getString(
                R.string.profile_concat_address,
                it[0].address,
                it[0].state,
                it[0].zip
            )
            tv_profile_country.text = it[0].country.addNoDataPlaceholder()
            tv_profile_phone.text = it[0].phone.addNoDataPlaceholder()
            tv_profile_website.text = it[0].website.addNoDataPlaceholder()
            tv_profile_industry.text =
                getString(R.string.profile_concat_industry, it[0].industry).addNoDataPlaceholder()
            tv_profile_sector.text =
                getString(R.string.profile_concat_sector, it[0].sector).addNoDataPlaceholder()
            tv_profile_employees.text = getString(
                R.string.profile_concat_employees,
                it[0].fullTimeEmployees.toString()
            ).addNoDataPlaceholder()
            tv_profile_description.text = it[0].description.addNoDataPlaceholder()
        })
        vm.progress.observe(viewLifecycleOwner, Observer {
            profile_content.isVisible = !it
            profile_progressBar.isVisible = it
        })
    }

}
