package com.diazzerss.stocks.presentation.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentProfileBinding
import com.diazzerss.stocks.utils.addNoDataPlaceholder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val vm: ProfileViewModel by viewModels()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindViewModel() {
        vm.profile.observe(viewLifecycleOwner, Observer {
            binding.tvProfileCeo.text = it[0].ceo.addNoDataPlaceholder()
            binding.tvProfileAddress.text = getString(
                    R.string.profile_concat_address,
                    it[0].address,
                    it[0].state,
                    it[0].zip
            )
            binding.tvProfileCountry.text = it[0].country.addNoDataPlaceholder()
            binding.tvProfilePhone.text = it[0].phone.addNoDataPlaceholder()
            binding.tvProfileWebsite.text = it[0].website.addNoDataPlaceholder()
            binding.tvProfileIndustry.text =
                    getString(R.string.profile_concat_industry, it[0].industry).addNoDataPlaceholder()
            binding.tvProfileSector.text =
                    getString(R.string.profile_concat_sector, it[0].sector).addNoDataPlaceholder()
            binding.tvProfileEmployees.text = getString(
                    R.string.profile_concat_employees,
                    it[0].fullTimeEmployees.toString()
            ).addNoDataPlaceholder()
            binding.tvProfileDescription.text = it[0].description.addNoDataPlaceholder()
        })
        vm.progress.observe(viewLifecycleOwner, Observer {
            binding.profileContent.isVisible = !it
            binding.profileProgressBar.isVisible = it
        })
    }

}
