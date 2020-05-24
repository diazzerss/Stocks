package com.diazzerss.stocks.ui.Company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.diazzerss.stocks.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    lateinit var ticker: String

    companion object {
        //TODO узнать про это
        fun newInstance() = ProfileFragment()
    }

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ticker = arguments?.getString("ticker").toString()

        initViewModel()

        return FragmentProfileBinding.inflate(inflater, container, false).root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val image = view.tv_profile_image
        val name = view.tv_profile_name
        val price = view.tv_profile_price
        val changes = view.tv_profile_changes
        val website = view.tv_profile_website
        val exchange = view.tv_profile_exchange
        val industry = view.tv_profile_industry
        val sector = view.tv_profile_sector
        val description = view.tv_profile_description
        profileViewModel.profileLiveData.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it[0].image).into(image)
            name.text = it[0].companyName
            price.text = "$".plus(it[0].price.toString())
            changes.text = "$".plus(it[0].changes.toString())
            website.text = it[0].website
            exchange.text = it[0].exchange
            industry.text = it[0].industry
            sector.text = it[0].sector
            description.text = it[0].description
        })


    }

    private fun initViewModel() {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        profileViewModel.getProfile(ticker)


    }

}
