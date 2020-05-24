package com.diazzerss.stocks.ui.Company

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.diazzerss.stocks.R

class GraphicsFragment : Fragment() {

    companion object {
        fun newInstance() = GraphicsFragment()
    }

    private lateinit var viewModel: GraphicsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graphics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GraphicsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
